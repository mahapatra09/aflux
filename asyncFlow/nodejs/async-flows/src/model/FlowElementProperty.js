/**
Property of a node
// related to backend ToolProperty
*/




class FlowElementProperty {
  constructor(name,value,initialValue,type,options,hint,html,readOnly) {
  this.name = name;
	this.value = value;
  this.type = type;
  this.options = options;
	this.initialValue = initialValue;
	this.hint = hint;
	this.html = html;
  this.readOnly = readOnly;
}
}

export default FlowElementProperty;
