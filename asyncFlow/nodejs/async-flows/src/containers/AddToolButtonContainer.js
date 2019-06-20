import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import addIcon from '../images/ic_add_black_24dp_1x.png'

class AddToolButtonContainer extends Component {
  render() {
    return (
      <div className="AddToolButtonContainer"  style={{width: this.props.width,bottom:this.props.bottom}}>
      <button type="button" title="Add Jar ToolSet" disabled={false}
      onClick={(event) => this.props.openPluginUploaderDialog()}><img src={addIcon} alt="Add Jar ToolSet"/></button>

      </div>
    );
  }
}

export default AddToolButtonContainer;
