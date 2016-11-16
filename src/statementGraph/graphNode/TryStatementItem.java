package statementGraph.graphNode;

import org.eclipse.jdt.core.dom.TryStatement;


public class TryStatementItem extends ElementItem{

	private TryStatement astNode; 
	
	public TryStatementItem(TryStatement astNode){
		this.astNode = astNode;
		super.setType(astNode.getNodeType());
		this.setLineCount(astNode.toString());
	}
	
	public TryStatement getASTNode(){
		return this.astNode;
	}
	
	@Override
	protected void setLineCount(String code) {
		//It should be the length excluding the body.
		super.lineCount = code.split(System.getProperty("line.separator")).length;
	}

	@Override
	public void printName() {
		System.out.print("Try Statement: "+astNode.toString());
	}
	
	@Override
	public void printDebug() {
		System.out.print("Try Statement: "+astNode.toString());
		System.out.println("Successor: -->");
		if(super.getCFGSeqSuccessor() == null){
			System.out.println("null");
		}
		else{
			super.getCFGSeqSuccessor().printName();
		}
	}

}