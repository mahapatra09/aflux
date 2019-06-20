/**
FlowPlugin	Information about a plugin.
Plugin are set of Node Types that can be loaded and used
*/

class FlowPlugin {
  constructor(id,jarName,jarLocation,activated,dynamicActivation) {
	this.id = id;
  this.jarName = jarName;
  this.jarLocation = jarLocation;
  this.activated = activated;
  this.dynamicActivation = dynamicActivation;
  }
}

export default FlowPlugin;
