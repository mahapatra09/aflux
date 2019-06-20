import React, { Component } from 'react';
import '../AsyncFlowsApp.css';


class UploadPluginDialog extends Component {
	constructor(props) {
		super(props);
		this.state={
			newName:this.props.oldName,
			files:null
		}
	}
	selectFile(event) {
		var inputFileElement=document.getElementById('uploadPluginButton');
		var fullPath = inputFileElement.value;
		var fileToUpload = inputFileElement.files[0];
		this.setState({
			newName:fullPath,
			file:fileToUpload
		});
	}
	componentDidMount() {
		this.inputLabel.focus();
	}
	changeName() {
		var newValue=this.state.newName;
		if (newValue!=="") {
			this.props.changeName(newValue);
		}
	}
	render(){
			var styleRect={opacity:0,fill:"#4387fd"}
		return (
<div className="UploadPluginDialogStyle" tabIndex="0" >
	<div className="JobSelectorHeader">
			<div className="CloseIcon" onMouseDown={(event) =>this.props.closeDialog()}>
				<span className="toolTipClose">Close</span>
				<div role="button" className="JobSelecta-b-c" tabIndex="0" >
					<div className="Jm-vu-db-Oh">
					<svg width="25px" height="25px">
						<g ><polygon points="10.5,11.914 13.5,14.914 14.914,13.5 11.914,10.5 14.914,7.5 13.5,6.086 10.5,9.086 7.5,6.086 6.085,7.5 9.085,10.5 6.085,13.5 7.5,14.914 " id="polygon6" transform="translate(-2.9561186,-3.5084746)"/>
<rect width="21" height="21" id="rect8" x="0" y="-6" style={styleRect} />
</g>
					</svg>
				</div>
			</div>

	</div>
	<div className="SaveAsDialogMainContent">
		<div className="DialogContent" >
			<div>
			<div id="fileUploadDiv" className="AcceptButton" style={{height:"29px",display:"inline-block"}}>
				<label ref={(input) => {this.inputLabel=input;}} htmlFor="uploadPluginButton" >{this.props.label}</label>
			</div>
			<span style={{marginLeft:"10px"}}>{this.state.newName}</span>
		<input id="uploadPluginButton" type="file" name="pluginFile" accept=".jar" style={{visibility:"hidden"}} onChange={(event)=>this.selectFile(event)}/>
				<div>
					<input type="button" className="AcceptButton" value="Accept" onMouseDown={(event) =>this.props.accept(this.state.newName,this.state.file)}/>
					<input type="button" className="CancelButton" value="Cancel" onMouseDown={(event) =>this.props.closeDialog()}/>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
	)
	}
}

export default UploadPluginDialog;
