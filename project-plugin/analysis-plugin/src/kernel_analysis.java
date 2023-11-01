import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.tracecompass.tmf.core.analysis.TmfAbstractAnalysisModule;
import org.eclipse.tracecompass.tmf.core.analysis.requirements.TmfAbstractAnalysisRequirement;
import org.eclipse.tracecompass.tmf.core.analysis.requirements.TmfAbstractAnalysisRequirement.PriorityLevel;
import org.eclipse.tracecompass.tmf.core.analysis.requirements.TmfAnalysisEventRequirement;
import org.eclipse.tracecompass.tmf.core.exceptions.TmfAnalysisException;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class kernel_analysis extends TmfAbstractAnalysisModule {

    public static final String PARAM1 = "myparam";
    public static final String PARAM2 = "myotherparam";

    @Override
    public Iterable<TmfAbstractAnalysisRequirement> getAnalysisRequirements() {

        // initialize the requirement: events
        Set<@NonNull String> requiredEvents = ImmutableSet.of("sched_switch", "sched_wakeup");
        TmfAbstractAnalysisRequirement eventsReq = new TmfAnalysisEventRequirement(requiredEvents, PriorityLevel.MANDATORY);

        return ImmutableList.of(eventsReq);
    }

    @Override
    protected void canceling() {
	     /* The job I am running in is being cancelled, let's clean up */
    }

    @Override
    protected boolean executeAnalysis(final IProgressMonitor monitor) {
        /*
         * I am running in an Eclipse job, and I already know I can execute
         * on a given trace.
         *
         * In the end, I will return true if I was successfully completed or
         * false if I was either interrupted or something wrong occurred.
         */
    	try {
	        Object param1 = getParameter(PARAM1);
	        int param2 = (Integer) getParameter(PARAM2);
	        System.out.println(param1);
	        System.out.println("param2: " + param2);
    	} catch(Exception e) {
    		return false;
    	}
    	return true;
    }

    @Override
    public Object getParameter(String name) {
        Object value = super.getParameter(name);
        /* Make sure the value of param2 is of the right type. For sake of
           simplicity, the full parameter format validation is not presented
           here */
        if ((value != null) && name.equals(PARAM2) && (value instanceof String)) {
            return Integer.parseInt((String) value);
        }
        return value;
    }

}
