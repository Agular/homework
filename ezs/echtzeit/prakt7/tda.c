#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

#define MIN(a, b) ((a) < (b) ? a : b)
/*
 The struct for a task
 */
typedef struct task{
    char name[4];
    float t_phi;
    float t_p;
    float t_e;
    float t_d;
    float u_i;
    float t_mr;
    char ok;
}task_t;

/*
 The struct for nodes, needed while reading them in dynamically.
 */
typedef struct node{ 
    task_t *task; 
    struct node *next;
    struct node *prev;
} node_t;

/*
 Function used to add a new node to the end of the list.
 */
void push(node_t *head, char *val){
    node_t *current = head;
    while(current->next){
        current = current->next;
    }
    current->next = malloc(sizeof(node_t));
    current->next->next = NULL;
    current->next->task = malloc(sizeof(task_t));
    current->next->prev = current;
    
    val = strtok(val, " ");
    strcpy(current->next->task->name, val);
    val = strtok(NULL, " ");
    current->next->task->t_phi = atof(val);
    val = strtok(NULL, " ");
    current->next->task->t_p = atof(val);
    val = strtok(NULL, " ");
    current->next->task->t_e = atof(val);
    val = strtok(NULL, " ");
    current->next->task->t_d = atof(val);
}

/*
 Pretty print our dynamic list
 */
int pretty_print(node_t *head){
    if(head == NULL){
        printf("The head is NULL, initialze list first!\n");
        return -1;
    }
    if (head->task == NULL){
        printf("The task is NULL, initalize task!\n");
        return -1;
    }
    node_t *current = head;
    float u = 0.0f;
    
    printf("Task     t_phi   t_p     t_e     t_d     u_i     t_mr    ok\n");
    do{
        printf(" %-8s%-8.2f%-8.2f%-8.2f%-8.2f%-8.4f%-8.2f%-c\n",
            current->task->name,
            current->task->t_phi,
            current->task->t_p,
            current->task->t_e,
            current->task->t_d,
            current->task->u_i,
            current->task->t_mr,
            current->task->ok
        );
        u += current->task->u_i;
        current = current->next;
    } while(current);
    printf("u = %.4f\n", u);
    return 0;
}

/*
 De-allocate our list from memory
 */
int free_dyn_list(node_t *head){
    if(head == NULL){
        printf("List is already empty");
        return -1;
    }
    
    node_t *curr = head;
    node_t *p;
    node_t *next;
    for(p = curr; NULL != p; p = next){
        next = p-> next;
        free(p->task);
        free(p);
    }
    head = NULL;
    return 0;
}

/*
 * Get the tail of the list
*/
node_t* get_tail(node_t *head){
    node_t *current = head;
    while(current->next){
        current = current->next;
    }
    return current;
} 

/*
 Calculate the u for the entire list
 */
int calculate_u_list(node_t *head){
    if(!head){
        printf("Head is NULL, cannot calculate u\n");
        return -1;
    }
    node_t *current = head;
    do{
        current->task->u_i = current->task->t_e / current->task->t_p;
        current = current->next;
    }
    while (current);
    return 0;
}

float calculate_mr(node_t *job, float t){
    node_t *current = job;
    float new_t = job->task->t_e;
    while(current->prev){
        current = current->prev;
        new_t += ceil(t / current->task->t_p) * current->task->t_e;
    }
    if (new_t == t){
        return new_t;
    } else {
        return calculate_mr(job, new_t);
    }
    return 0.0f;
}

int calculate_mr_list(node_t *head){
    if(head == NULL){
        printf("Head is NULL, cannot calculate mr\n");
        return -1;
    }
    node_t *current = head;
    while (current){
        current->task->t_mr = calculate_mr(current, current->task->t_e);
        if(current->task->t_mr <current->task->t_p){
            current->task->ok = 'v';
        } else{
            current->task->ok = 'x';
        }
        current = current->next;
    }
    
    return 0;
}


/*
 * Function used to replace chars with another char in a string
 * Returns: pointer to the changed string
 */
char* strchn(char *str, char needle, char new){
    char *pos = strchr(str, needle);
    while(pos){
        *pos = new;
        pos = strchr(pos+1, needle);
    }
    return str;
}

int main(int argc, char **argv){
    
    FILE *fp;
    char* filename = argv[1];
    char buf[128];
    
    
    if(argc != 2){
        printf("usage: tda <filename>\n");
        exit(EXIT_FAILURE);
    }
    
    /*Try opening the file*/
    if ((fp = fopen(filename, "rt")) == NULL){
        printf("Could not open file, check filename and location!\n");
        exit(EXIT_FAILURE);
    }
    /*
     Create the dynamic list head.
     */
    node_t *head = NULL;
    head = malloc(sizeof(node_t));
    if (head == NULL){
        printf("Failed to malloc to head, exiting...\n");
        exit(EXIT_FAILURE);
    }
    
    /*
     File open was successful, start reading and saving.
     */
    while(fgets(buf, 127, fp) != NULL){
        char *temp = strchn(buf, '(',' ');
        temp = strchn(buf, ')','\0');
        temp = strchn(buf, ',',' ');
        
        if(head->task != NULL){
            /*Add item at the end of the list*/
            push(head, temp);
        } else{
            /*Allocating memory for head*/
            head->task = malloc(sizeof(task_t));
            head->next = NULL;
            head->prev = NULL;
            temp = strtok(temp, " ");
            strcpy(head->task->name, temp);
            temp = strtok(NULL, " ");
            head->task->t_phi = atof(temp);
            temp = strtok(NULL, " ");
            head->task->t_p = atof(temp);
            temp = strtok(NULL, " ");
            head->task->t_e = atof(temp);
            temp = strtok(NULL, " ");
            head->task->t_d = atof(temp);
            
        }
    }
    fclose(fp);
/*
 Calculation part comes here
 */    
    calculate_u_list(head);
    calculate_mr_list(head);
/*
 Pretty print everything out.
 */
pretty_print(head);
/*
 Release all node items from memory.
 */
free_dyn_list(head);

    return 0;
}