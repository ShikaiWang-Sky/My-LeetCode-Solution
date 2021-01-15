package leetcode.editor.cn.FSM;

import lombok.Getter;

/**
 * 动作基类
 *
 * @author Wang
 */
public abstract class Transition {
    /**
     * 触发事件
     */
    @Getter
    private String eventCode;

    /**
     * 触发当前状态
     */
    @Getter
    private State currentState;

    /**
     * 触发后的状态
     */
    @Getter
    private State nextState;

    public Transition(String eventCode, State currentState, State nextState) {
        this.eventCode = eventCode;
        this.currentState = currentState;
        this.nextState = nextState;
    }

    /**
     * 执行动作
     * @author Wang
     * @param event
     * @return
     */
    public State execute(Event event) {
        System.out.printf("当前是：%s 状态，执行：%s 操作后，流转成：%s 状态。%n"
                , currentState, eventCode, nextState);
        if (this.doExecute(event)) {
            return this.nextState;
        } else {
            return null;
        }
    }

    /**
     * 执行动作的具体业务
     * @author Wang
     * @param event
     * @return
     */
    protected abstract boolean doExecute(Event event);
}
