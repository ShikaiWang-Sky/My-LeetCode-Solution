package leetcode.editor.cn.FSM;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 状态机基类
 *
 * @author Wang
 */
@Slf4j
public abstract class StateMachine {
    /**
     * 定义所有的状态
     */
    private static List<State> allStates = null;

    /**
     * 状态机执行事件
     * @param stateCode
     * @param event
     * @return
     */
    public State execute(String stateCode, Event event) {
        State startState = this.getState(stateCode);
        for (Transition transition : startState.getTransitions()) {
            if (event.getEventCode().equals(transition.getEventCode())) {
                return transition.execute(event);
            }
        }
        log.error("StateMachine[{}] Can not find transition for stateId[{}] eventCode[{}]", this.getClass().getSimpleName(), stateCode, event.getEventCode());
        return null;
    }

    public State getState(String stateCode) {
        if (allStates == null) {
            log.info("StateMachine declareAllStates");
            allStates = this.declareAllStates();
        }
        for (State state : allStates) {
            if (state.getStateCode().equals(stateCode)) {
                return state;
            }
        }
        return null;
    }

    /**
     * 由具体的状态机定义的所有状态
     * @author Wang
     * @return
     */
    public abstract List<State> declareAllStates();

}
