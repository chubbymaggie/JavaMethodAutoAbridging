package statementGraph.graphNode;

import org.eclipse.jdt.core.dom.BreakStatement;


public class BreakStatementWrapper extends StatementWrapper{

	private BreakStatement astNode; 
	
	
	public BreakStatementWrapper(BreakStatement astNode){
		this.astNode = astNode;
		super.setType(astNode.getNodeType());
	}
	
	public BreakStatement getASTNode(){
		return this.astNode;
	}
	
	@Override
	public void printName() {
		System.out.print("Break Statement: "+astNode.toString());
	}

	@Override
	public void printDebug() {
		System.out.print("Break Statement: "+astNode.toString());
		System.out.println("Successor: -->");
		if(super.getCFGSeqSuccessor() == null){
			System.out.println("null");
		}
		else{
			super.getCFGSeqSuccessor().printName();
		}
		super.printDDGPredecessor();
	}
	

	@Override
	public String toString() {
		return astNode.toString();
	}

	@Override
	public int getLineCount() {
		return astNode.toString().split(System.getProperty("line.separator")).length;
	}

	@Override
	public String computeOutput(int level) {
		return super.computeIndent(level)+astNode.toString().substring(0,astNode.toString().length()-1);
	}
}

