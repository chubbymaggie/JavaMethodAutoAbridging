package learning.generateUserStudy;

import java.util.ArrayList;

import org.eclipse.core.runtime.Assert;
import org.json.JSONArray;
import org.json.JSONObject;

import ilpSolver.LearningBinaryIPSolverV5;
import ilpSolver.NaiveBinaryIPSolver;

public class Type1Question extends Question{
	private ArrayList<LearningBinaryIPSolverV5> solvers = new ArrayList<LearningBinaryIPSolverV5>();
	private ArrayList<NaiveBinaryIPSolver> navieSolvers = new ArrayList<NaiveBinaryIPSolver>();
	
	private String doc;
	
	public Type1Question(JSONObject input) throws Exception{
		JSONArray codes = input.getJSONArray("codeA");
		for(int i=0;i<codes.length();i++){
			JSONObject code = codes.getJSONObject(i);
			this.solvers.add(this.buildSolver(code));
			this.navieSolvers.add(this.buildNaiveSolver(code));
		}
		this.doc = input.getString("doc");
	}
	
	public void setParas(double[] para){
		for(LearningBinaryIPSolverV5 solver:this.solvers){
			solver.setParameters(para);
		}
	}
	
	public void setTargetLineCounts(int line){
		for(LearningBinaryIPSolverV5 solver:this.solvers){
			solver.setTargetLineCount(line);
		}
		for(NaiveBinaryIPSolver solver:this.navieSolvers){
			solver.setTargetLineCount(line);
		}
	}
	
	public void setTargetLineCounts(double rate){
		Assert.isTrue(rate>0 && rate<1);
		for(int i=0; i < this.solvers.size(); i++){
			int originalLines = this.solvers.get(i).originalProgramLineCount();
			int targetLine = (int)(originalLines * rate);
			Assert.isTrue(targetLine>=1);
			this.solvers.get(i).setTargetLineCount(targetLine);
			this.navieSolvers.get(i).setTargetLineCount(targetLine);
		}
	}
	
	public JSONObject computeOutput(int method){
		JSONObject result = new JSONObject();
		JSONArray codes = new JSONArray();
		if(method==EncoderUtils.ORIGINAL){
			for(LearningBinaryIPSolverV5 solver:this.solvers){
				codes.put(solver.originalProgram2String());
			}
		}
		else if(method==EncoderUtils.NAIVEMETHOD){
			for(NaiveBinaryIPSolver solver:this.navieSolvers){
				codes.put(solver.outputSolveResult());
			}
		}
		else if(method==EncoderUtils.MYMETHOD){
			for(LearningBinaryIPSolverV5 solver:this.solvers){
				codes.put(solver.outputSolveResult());
			}
		}
		result.put("doc", this.doc);
		result.put("type", "T1");
		result.put("method", method);
		return result;
	}
}