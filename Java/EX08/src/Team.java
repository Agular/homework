import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ragnar on 08.03.2016.
 * Team class.
 */
public class Team {
    /**
     * The code name of the Team.
     */
    private String codeName;
    /**
     * The list containing the Soldiers of the Team.
     */
    private List<SoldierOfFortune> members;
    /**
     * The list containing completed missions by the Team.
     */
    private List<Mission> completedMissions = new ArrayList<>();

    /**
     * The default constructor to create a Team class.
     */
    public Team() {

    }

    /**
     * Set the code nme of the team.
     *
     * @param codeName Given code name.
     */
    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    /**
     * The custom constructor for the Team.
     *
     * @param codeName Given codename of the team.
     */
    public Team(String codeName) {
        this.codeName = codeName;
    }

    /**
     * Return the codename of the Team.
     *
     * @return codeName.
     */
    public String getCodeName() {
        return codeName;
    }

    /**
     * Set the members of the Team.
     *
     * @param members The list of Soldiers.
     */
    public void setMembers(List<SoldierOfFortune> members) {
        this.members = members;
    }

    /**
     * Add a Soldier to the Team.
     *
     * @param soldier A soldier.
     */
    public void addSoldierToTeam(SoldierOfFortune soldier) {
        if (members == null) {
            members = new ArrayList<SoldierOfFortune>();
        }
        if (soldier != null && !members.contains(soldier)) {
            members.add(soldier);
        }
    }

    /**
     * Return the list of members of the Team.
     *
     * @return members.
     */
    public List<SoldierOfFortune> getMembers() {
        return members;
    }

    /**
     * Return the number of Soldiers in the list.
     *
     * @return The number of Soldiers in members.
     */
    public int getNumberOfSoldiers() {
        if (members == null) {
            return 0;
        }
        List<SoldierOfFortune> controlled = new ArrayList<>();
        int number = 0;
        for (SoldierOfFortune member : members) {
            if (member != null && !controlled.contains(member)) {
                number++;
                controlled.add(member);
            }
        }
        return number;
    }

    /**
     * Return the codename and the names of the members of the Team as a String.
     *
     * @return string.
     */
    public String toString() {
        if (members == null || members.isEmpty()) {
            return codeName + ": ";
        } else if (codeName == null) {
            String string = "";
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i) != null) {
                    string += members.get(i).getCodeName();
                    if (members.size() - i > 1) {
                        string += ", ";
                    }
                }

            }
            return string;
        } else {
            String string = codeName + ": ";
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i) != null) {
                    string += members.get(i).getCodeName();
                    if (members.size() - i > 1) {
                        string += ", ";
                    }
                }
            }
            return string;
        }
    }

    /**
     * Makes each member of the Team dance.
     */
    public void dance() {
        for (SoldierOfFortune member : members) {
            member.dance();
        }
    }

    /**
     * Average missions completed by all of the soldiers in the team.
     *
     * @return The average completed.
     */
    public double averageMissionsCompleted() {
        if (members == null || members.size() == 0) {
            return 0.0;
        } else {
            int missions = 0;
            List<SoldierOfFortune> soldiers = new ArrayList<>();
            for (int i = 0; i < members.size(); i++) {
                if (members.get(i) != null && !soldiers.contains(members.get(i))) {
                    soldiers.add(members.get(i));
                    missions += members.get(i).getNumberOfMissionsCompleted();
                }
            }
            return (double) missions / (double) soldiers.size();
        }
    }

    /**
     * Send the team to the Mission.
     *
     * @param mission The missions to be sent to.
     * @return True if mission was completed.
     */
    public boolean sendToMission(Mission mission) {
        if (mission == null) {
            return false;
        } else {
            if (mission.receiveTeam(this)) {
                completedMissions.add(mission);
                for (SoldierOfFortune member : members) {
                    if (member != null) {
                        member.setNumberOfMissionsCompleted(member.getNumberOfMissionsCompleted() + 1);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Return the list of completed missions.
     *
     * @return completedMissions.
     */
    public List<Mission> getCompletedMissions() {
        return completedMissions;
    }
}
