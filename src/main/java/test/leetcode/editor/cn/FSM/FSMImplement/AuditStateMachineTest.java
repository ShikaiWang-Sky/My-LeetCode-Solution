package test.leetcode.editor.cn.FSM.FSMImplement;

import leetcode.editor.cn.FSM.Event;
import leetcode.editor.cn.FSM.FSMImplement.AuditStateMachine;
import leetcode.editor.cn.FSM.State;
import leetcode.editor.cn.FSM.StateMachine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * AuditStateMachine Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>1月 15, 2021</pre>
 */
public class AuditStateMachineTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: declareAllStates()
     */
    @Test
    public void testDeclareAllStates() throws Exception {
        StateMachine sm = new AuditStateMachine();
        State state = sm.execute(AuditStateMachine.StateCodeContents.PENDING, new Event(AuditStateMachine.EventCodeContents.PASS));
    }

    /**
     * Method: doExecute(Event event)
     */
    @Test
    public void testDoExecuteEvent() throws Exception {
    }

} 
