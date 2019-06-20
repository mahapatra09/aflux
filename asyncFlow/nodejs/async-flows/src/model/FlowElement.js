/**
Node.
*/


import FlowElementProperty from './FlowElementProperty';

class FlowElement {
  constructor(id,
    name,
    elementType,
    x,y,
    width,
    height,
    inputInterfaces,
    outputInterfaces,
    dispatcher,
    mailbox,
    asyncInterface,
    color,
    elementBaseProperties,
    subFlow) {
	this.id = id;
  this.name = name;
	this.type = elementType;
	this.color = color;
	this.x = x;
	this.y = y;
	// TODO: width could be calculated from name
	this.width = width;
	this.height = height;
	this.inputInterfaces=inputInterfaces;
	this.outputInterfaces=outputInterfaces;
	this.deleted=false;
  this.properties=[];
  this.propertyValues=[];
  this.dispatcher=dispatcher;
  this.mailbox=mailbox;
  this.asyncInterface=asyncInterface;
  this.subFlow=subFlow;

/*
  if (elementBaseProperties!=null) {
    // add base properties
    for (var i=0;i<elementBaseProperties.length;i++) {
      var propertyType=elementBaseProperties[i];
      switch (propertyType.name) {
        case 'name':
          var propertyValue = name;
          break;
        case 'color':
          var propertyValue = color;
          break;
        case 'dispatcher':
          var propertyValue = dispatcher;
          break;
        case 'mailbox':
          var propertyValue = mailbox;
          break;
        case 'width':
          var propertyValue = width;

      }
      this.properties.push(new FlowElementProperty(
        propertyType.name,
        propertyValue,
        propertyValue,
        propertyType.hint,
        propertyType.html));
    }
  }
  */
  if (elementType.properties!=null) {
    // add base properties
    for (var i=0;i<elementType.properties.length;i++) {
      var propertyType=elementType.properties[i];
      this.properties.push(new FlowElementProperty(
        propertyType.name,
        propertyType.initialValue,
        propertyType.initialValue,
        propertyType.type,
        propertyType.options,
        propertyType.hint,
        propertyType.html,
        propertyType.readOnly));
      this.propertyValues.push(propertyType.initialValue);
    }
  }
}
  deleteInstance() {
	  this.deleted=true;
  }
}

export default FlowElement;
