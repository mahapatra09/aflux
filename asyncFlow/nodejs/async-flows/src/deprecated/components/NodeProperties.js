import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class NodeProperties extends Component {
  changeProperty(event,id) {
    this.props.changeProperty(id,event.target.value);
  }
  render() {
    var propertiesEditor=
    <div className="PropertiesContainer" style={this.props.divStyle} >
      <div className="PropertiesMainContent" style={this.props.divStyle}>
          <div className="PropertiesInnerContent">
              <div className="PropertiesMainTitle">{this.props.title}</div>
              <div className="PropertiesContent">
                  <div className="PropertiesSection">
                  {this.props.properties.map((element,i) =>
                  <div className="PropertiesField" key={i} >
                  <span className="PropertiesLabel">{element.name}</span>
                  <span className="PropertiesInput">
                      <input name={"property"+i}
                        type="text"
                        onChange={(event) => this.changeProperty(event,i) }
                        value={element.value} />
                  </span>
                  </div>)}

                  </div>
              </div>
          </div>
      </div>
    </div>
    return propertiesEditor;

  }

}

export default NodeProperties;
