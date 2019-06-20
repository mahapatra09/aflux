/**
A link between two nodes
*/
class FlowConnector {
	constructor(id,sourceElement,sourceIndex,targetElement,targetIndex) {
		this.id=id;
		this.sourceId=sourceElement.id;
		if (targetElement==null) {
			this.targetId=0;
		} else {
			this.targetId=targetElement.id;
		}
		this.targetIndex=targetIndex;
		this.sourceIndex=sourceIndex;
		this.sourceElement=sourceElement;
		this.targetElement=targetElement;
		this.deleted=false;
	}
  deleteInstance() {
	  this.deleted=true;
  }

}

export default FlowConnector;
