package algorithm;
//Breadth First Search Algorithm
//Complexity is O(b^d)
//Created By Armin

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {

    public static ArrayList<Action> search(Problem p){

        Queue<StateActionSequence> BFSQueue = new LinkedList<>();
        ArrayList<State> closed = new ArrayList<>();

        StateActionSequence initSAS = new StateActionSequence();
        initSAS.state = p.initialState();
        initSAS.actionSequence = new ArrayList<>();

        BFSQueue.add(initSAS);
        while(!BFSQueue.isEmpty()){
            StateActionSequence s = BFSQueue.remove();
            if(p.goalTest(s.state)){
                //Goal Reached
                System.out.println("[BFS] Goal Reached !");
                return s.actionSequence;
            }else{
                //Expand Childs
                for(Action a : p.actions(s.state)){
                    State targetState = p.result(s.state,a);
                    boolean mustAdd = true;
                    for(State closedState : closed){
                        if(closedState.isEquals(targetState)){
                            mustAdd = false;
                            break;
                        }
                    }
                    for(StateActionSequence openState : BFSQueue){
                        if(openState.state.isEquals(targetState)){
                            mustAdd = false;
                            break;
                        }
                    }
                    if(mustAdd) {
                        StateActionSequence SAS = new StateActionSequence();
                        SAS.state = targetState;
                        //Clone Parent Action Sequence
                        ArrayList<Action> asClone = new ArrayList<>();
                        for (Action sa : s.actionSequence) {
                            asClone.add(sa);
                        }
                        asClone.add(a);
                        SAS.actionSequence = asClone;
                        BFSQueue.add(SAS);
                    }
                }
            }
        }
        //There is no answer to algorithm.Problem
        System.err.println("[BFS] No Answer !");
        return null;
    }

}