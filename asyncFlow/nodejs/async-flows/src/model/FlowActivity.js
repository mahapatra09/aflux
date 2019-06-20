/**
FlowActivity	Activity is represented by a Tab in the View Panel .
It has a name and contains a flow
*/

class FlowActivity {
  constructor(id,name,index,elements,connectors,properties,parentActivityId,parentNodeId) {
	this.id = id;
  this.name = name;
	this.index = index;
	this.elements=elements;
	this.connectors=connectors;
  this.properties=this.generateProperties(properties);
	this.deleted=false;
  this.parentActivityId=parentActivityId;
  this.parentNodeId=parentNodeId;


  /*
    Change to get from a service in order to no replicate java Code
  */
  }

  deleteInstance() {
	  this.deleted=true;
  }

  generateProperties(properties) {
    if (properties==null) {
      var result=null;
    } else {
      if (properties.length===0) {
          result=[];
      } else {
        // var result=properties.slice(0);
        result = properties.map(a => Object.assign({}, a));
        result[0].value=this.name;
      }
    }
    return result;
  }
}

export default FlowActivity;
