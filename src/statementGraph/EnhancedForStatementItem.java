package statementGraph;


import org.eclipse.jdt.core.dom.EnhancedForStatement;

public class EnhancedForStatementItem extends ElementItem{

	private EnhancedForStatement astNode; 
	
	public EnhancedForStatementItem(EnhancedForStatement astNode){
		super.setType(astNode.getNodeType());
		this.setLineCount(astNode.toString());
		this.astNode = astNode;
	}
	
	public EnhancedForStatement getASTNode(){
		return this.astNode;
	}
	
	@Override
	protected void setLineCount(String code) {
		//It should be the length excluding the body.
		int total = code.split(System.getProperty("line.separator")).length;
		int body = astNode.getBody().toString().split(System.getProperty("line.separator")).length;
		super.lineCount = total - body; //Maybe problematic, check again! 
	}
}

