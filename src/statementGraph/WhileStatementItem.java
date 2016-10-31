package statementGraph;

import org.eclipse.jdt.core.dom.WhileStatement;

public class WhileStatementItem extends ElementItem{

	private WhileStatement astNode; 
	
	public WhileStatementItem(WhileStatement astNode){
		super.setType(astNode.getNodeType());
		this.setLineCount(astNode.toString());
		this.astNode = astNode;
	}
	
	public WhileStatement getASTNode(){
		return this.astNode;
	}
	
	@Override
	protected void setLineCount(String code) {
		//It should be the length excluding the body.
		super.lineCount = code.split(System.getProperty("line.separator")).length;
	}
}
