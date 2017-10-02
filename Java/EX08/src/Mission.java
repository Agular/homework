/**
 * Created by Ragnar on 08.03.2016.
 * Missions class.
 */
public class Mission {
    /**
     * The code name of the String.
     */
    private String codeName = "";
    /**
     * The numbers of required missions completed in order to complete this Mission.
     */
    private int requiredMissionsCompleted;
    /**
     * The boolean to show the state of completion of the Mission.
     */
    private boolean completion = false;

    /**
     * The default constructor for the Mission.
     */
    public Mission() {

    }

    /**
     * The custom constructor of the mission.
     *
     * @param codeName The code name of the mission.
     */
    public Mission(String codeName) {
        this.codeName = codeName;
    }

    /**
     * Set the codename of the Mission.
     *
     * @param codeName The code name of the Mission.
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * Return the codename of the Mission as a string.
     *
     * @return A nice output of the Mission code name.
     */
    public String toString() {
        return "Operation " + codeName;
    }

    /**
     * Set the number of required missions in order to complete this Mission.
     *
     * @param requiredMissionsCompleted The number of missions needed.
     */
    public void setRequiredMissionsCompleted(int requiredMissionsCompleted) {
        this.requiredMissionsCompleted = requiredMissionsCompleted;
    }

    /**
     * Receive the Team and checks if the Team is able to complete this Mission.
     *
     * @param team The given team to complete this mission.
     * @return True if the mission was completed.
     */
    public boolean receiveTeam(Team team) {
        if (team == null || team.getMembers() == null || team.getMembers().isEmpty()) {
            return false;
        } else {
            if (team.averageMissionsCompleted() >= requiredMissionsCompleted) {
                completion = true;
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Return the state of completion of this Mission.
     *
     * @return completion.
     */
    public boolean isCompleted() {
        return completion;
    }
}
