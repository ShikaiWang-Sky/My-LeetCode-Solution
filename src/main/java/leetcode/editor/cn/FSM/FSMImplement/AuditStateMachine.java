package leetcode.editor.cn.FSM.FSMImplement;

import leetcode.editor.cn.FSM.Event;
import leetcode.editor.cn.FSM.State;
import leetcode.editor.cn.FSM.StateMachine;
import leetcode.editor.cn.FSM.Transition;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单的请假审批状态机
 *
 * @author Wang
 */
public class AuditStateMachine extends StateMachine {

    @Override
    public List<State> declareAllStates() {
        List<State> stateList = new ArrayList<>();

        State pendingState = new State(StateCodeContents.PENDING);
        State passedState = new State(StateCodeContents.PASSED);
        State refusedState = new State(StateCodeContents.REFUSED);

        pendingState.addTransition(new PassTransition(pendingState, passedState));
        pendingState.addTransition(new RefuseTransition(pendingState, refusedState));

        stateList.add(pendingState);
        stateList.add(passedState);
        stateList.add(refusedState);

        return stateList;
    }

    /**
     * 定义“通过”动作
     */
    public class PassTransition extends Transition {
        public PassTransition(State currState, State nextState) {
            super(EventCodeContents.PASS, currState, nextState);
        }

        @Override
        protected boolean doExecute(Event event) {
            System.out.println("执行通过操作...");
            return true;
        }

    }

    /**
     * 定义“拒绝”动作
     */
    public class RefuseTransition extends Transition {
        public RefuseTransition(State currState, State nextState) {
            super(EventCodeContents.REFUSE, currState, nextState);
        }

        @Override
        protected boolean doExecute(Event event) {
            System.out.println("执行拒绝操作...");
            return true;
        }

    }

    /**
     * 事件编码
     */
    public class EventCodeContents {
        public static final String PASS = "审核通过";
        public static final String REFUSE = "审核拒绝";
    }

    /**
     * 状态编码
     */
    public class StateCodeContents {
        public static final String PENDING = "待审批";
        public static final String PASSED = "审批通过";
        public static final String REFUSED = "审批拒绝";
    }
}


