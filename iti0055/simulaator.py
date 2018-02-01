# -*- coding: utf-8 -*-
"""
Created on Thu Feb  1 13:53:19 2018

@author: raluga
"""

def vacworld_sim(initAgentLoc = "A", initRoomsConditions = [True, True], steps = 10):
    
    agentLocation = initAgentLoc;
    roomsConditions = initRoomsConditions;
    
    state = {
            "lastLocation": None,
            "roomConditions" : [None, None]};
    
    for step in range(1, steps + 1):
        # call agent program with data about the current environment
        # perform agent action (update world state)
        
        #action = reflex_agent(agentLocation, getRoomCondition(agentLocation, roomsConditions));
        
        action, state = state_reflex_agent(agentLocation, getRoomCondition(agentLocation, roomsConditions),state)
        
        if action == "NoOp":
            print("Agent does nothing")
            pass
        elif action == "Left":
            agentLocation = "A";
            pass
        elif action == "Right":
            agentLocation = "B";
        elif action == "Suck":
            if agentLocation == "A":
                roomsConditions[0] = False
            else:
                roomsConditions[1] = False
        else:
            raise NotImplementedError
            
        print("step", step,"Robot at", agentLocation, "roomConditions", roomsConditions)
            
def getRoomCondition(agentLocation, roomsConditions):
    
    if agentLocation == "A":
        return roomsConditions[0]
    elif agentLocation == "B":
        return roomsConditions[1]
    else:
        raise NotImplementedError


def reflex_agent(location, roomIsDirty):
    # decide action based on environment perception
    if roomIsDirty:
        return "Suck";
    elif location == "A":
        return "Right"
    else:
        return "Left"
    
def state_reflex_agent(location, roomIsDirty, state):
    action = None;
    localState = state;
    # decide action based on environment perception
        # decide action based on environment perception
    if roomIsDirty:
        action = "Suck";
    elif location == "A":
        action = "Right"
    else:
        action = "Left"
    return action, localState

if __name__ == "__main__":
    vacworld_sim();