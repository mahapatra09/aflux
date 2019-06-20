/**
It is the template of a node.
 Generally FlowElementType are showed in Left Tool Panel
*/


class FlowElementType {
  constructor(id,name,asyncInterface,inputInterfaces,outputInterfaces,color) {
	this.id = id;
    this.name = name;
	this.inputInterfaces=inputInterfaces;
	this.outputInterfaces=outputInterfaces;
	this.color=color;
  this.asyncInterface=asyncInterface;
  }
}

export default FlowElementType;
