import React, { Component } from 'react';
import '../AsyncFlowsApp.css';

class FooterContainer extends Component {
  getOutputTop() {
    var outputElement=document.getElementById("stdoutput");
    var rect = outputElement.getBoundingClientRect();
    return rect.top;

  }
  getFooterTop() {
    var footerSplitElement=document.getElementById("footerSplit");
    var rect = footerSplitElement.getBoundingClientRect();
    var footerSplitTop=rect.top;
    return footerSplitTop;
  }
  render() {

    return (
      <footer className="FooterContainer" style={{height:this.props.height,
		top:this.props.top}}>
	  <div className="StdOutput">

	  <pre id="stdoutput" >

    {this.props.stdOutput.map((element,i)=>
      <div key={i}>{element}
      </div>)}
		</pre>
		</div>
      </footer>
    );
  }
}

export default FooterContainer;
