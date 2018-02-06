# -*- coding: utf-8 -*-
"""
Created on Thu Feb  1 13:53:19 2018

@author: raluga
"""


class Agent:

    state = {
            "lastMove": None}

    def getReflexAction(self, location, roomIsDirty):
        # decide action based on environment perception
        if roomIsDirty:
            return "Suck"
        elif location == "A":
            return "Right"
        else:
            return "Left"


    def getStateReflexAction(self, location, roomIsDirty):
        # decide action based on environment perception
        if roomIsDirty:
            self.state["lastMove"] = "Suck"
            return "Suck"
        elif self.state["lastMove"] == "Suck":
            if location == "A":
                self.state["lastMove"] = "Right"
                return "Right"
            else:
                self.state["lastMove"] = "Left"
                return "Left"
        else:
            self.state["lastMove"] = "NoOp"
            return "NoOp"


def vacworld_sim(initAgentLoc = "A", initRoomsConditions = [True, True], steps=10):
    
    agentLocation = initAgentLoc
    roomsConditions = initRoomsConditions

    simpleAgent = Agent()


    if roomsConditions[0] and roomsConditions[1]:
        condition = "BOTH ROOMS ARE DIRTY"
    elif roomsConditions[0] or roomsConditions[1]:
        condition = "ONE ROOM IS DIRTY"
    else:
        condition = "ROOMS ARE CLEAN"

    currentScore = {"score": 0,
                    "condition": condition}

    for step in range(1, steps + 1):

        agentAction = simpleAgent.getStateReflexAction(agentLocation, getRoomCondition(agentLocation, roomsConditions))

        if agentAction == "NoOp":
            pass
        elif agentAction == "Left":
            agentLocation = "A"
            pass
        elif agentAction == "Right":
            agentLocation = "B"
        elif agentAction == "Suck":
            if agentLocation == "A":
                roomsConditions[0] = False
            else:
                roomsConditions[1] = False
        else:
            raise NotImplementedError
            
        print("step", step, "Robot at", agentLocation, "roomConditions", roomsConditions)
        currentScore = calculateScore(currentScore, agentAction, roomsConditions)
        print("Used energy:", currentScore["score"], " Room Condition:", currentScore["condition"])


def getRoomCondition(agentLocation, roomsConditions):
    
    if agentLocation == "A":
        return roomsConditions[0]
    elif agentLocation == "B":
        return roomsConditions[1]
    else:
        raise NotImplementedError


def calculateScore(currentScore, agentAction, roomsConditions):
    newScore = currentScore
    if agentAction != "NoOp":
        newScore["score"] += 1
    if roomsConditions[0] and roomsConditions[1]:
        newScore["condition"] = "BOTH ROOMS ARE DIRTY"
    elif roomsConditions[0] or roomsConditions[1]:
        newScore["condition"] = "ONE ROOM IS DIRTY"
    else:
        newScore["condition"] = "ROOMS ARE CLEAN"
    return newScore


if __name__ == "__main__":
    vacworld_sim()
