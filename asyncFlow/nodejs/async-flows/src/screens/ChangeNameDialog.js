import React, { Component } from 'react';
import '../AsyncFlowsApp.css';


class ChangeNameDialog extends Component {
	constructor(props) {
		super(props);
		this.state={
			newName:this.props.oldName
		}
		this.onChange = this.onChange.bind(this);
	}
	componentDidMount() {
		this.inputBox.focus();
	}
	changeName() {
		var newValue=this.state.newName;
		if (newValue!=="") {
			this.props.changeName(newValue);
		}
	}
	onChange(event) {
		this.setState({
			newName:event.target.value
		});
	}
	render(){
			var styleRect={opacity:0,fill:"#4387fd"}
		return (
<div className="SaveAsDialogStyle" tabIndex="0" >
	<div className="JobSelectorHeader">


			<div className="CloseIcon" onMouseDown={(event) =>this.props.closeSaveAsDialog()}>
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

		<div className="SaveAsDialogContent" >
			<div>
				<div>{this.props.label}<input
							id="saveAsInput"
							type="text"
							onChange={this.onChange}
							className="InputSaveAs"
							name="newName"
							value={this.state.newName}
							ref={(input) => {this.inputBox=input;}} /></div>
				<div>
				<input type="button" className="AcceptButton" value="Accept" onMouseDown={(event) =>this.changeName()}/>
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

export default ChangeNameDialog;
