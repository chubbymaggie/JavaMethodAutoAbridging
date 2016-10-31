package statementGraph;

import org.eclipse.jdt.core.dom.BreakStatement;


public class BreakStatementItem extends ElementItem{

	private BreakStatement astNode; 
	
	
	public BreakStatementItem(BreakStatement astNode){
		super.setType(astNode.getNodeType());
		this.setLineCount(astNode.toString());
		this.astNode = astNode;
	}
	
	public BreakStatement getASTNode(){
		return this.astNode;
	}
	
	@Override
	protected void setLineCount(String code) {
		super.lineCount = code.split(System.getProperty("line.separator")).length;
	}

}

