import React, { Component } from 'react';
import '../AsyncFlowsApp.css';
import ActivityTitle from '../components/ActivityTitle';
import ChangeNameDialog from '../screens/ChangeNameDialog';

class HeaderContainer extends Component {
	constructor(props) {
		super(props);
		this.state= {
			menuJobSelected:false,
			saveAsDialogOpened:false,
			renameDialogOpened:false,
			jobList:[],
		}
	}
	showJobPopup() {
		this.setState({
			menuJobSelected:true
		})
	}
	hideJobPopup() {
		this.setState({
			menuJobSelected:false
		})
	}
	saveJob() {
		this.props.saveJob();
		this.hideJobPopup();
	}
	openJob(flowJob) {
		this.setState({
			jobSelectorOpened:false
		});
		this.props.openJob(flowJob);
	}
	openSaveAsDialog() {
		this.setState({
			saveAsDialogOpened:true,
		});
	}
	closeSaveAsDialog() {
		this.setState({
			saveAsDialogOpened:false
		});

	}
	openRenameDialog() {
		this.setState({
			renameDialogOpened:true
		});
	}
	closeRenameDialog() {
		this.setState({
			renameDialogOpened:false
		});

	}
	saveAs(name) {
		this.props.saveAs(name);
		this.setState({
			saveAsDialogOpened:false
		});
	}
	renameJob(name) {
		this.props.renameJob(name);
		this.setState({
			renameDialogOpened:false
		});
	}
  render() {
	 const tdStyle = {
		 paddingRight: "6px", textAlign:"right"
	};

    return (
      <header className="HeaderContainer">
		<div className="FluxLogoSection"><span>aFlux</span></div>
		<ActivityTitle jobName={this.props.jobName} />
		<div className="FluxMainMenu">
			<div className="FluxMenuBar">
				<a className="flowMenuItem" onMouseDown={(event)=> this.showJobPopup()}>Job</a>
				<a className="flowMenuItem">Component</a>
				<a className="flowMenuItem">Activity</a>
				<a className="flowMenuItem" onMouseDown={(event) => this.props.prepareOpenPluginManager()}>Plugins</a>
				<a className="flowMenuItem">Settings</a>
				<a className="flowMenuItem">View</a>
				<a className="flowMenuItem">Help</a>
			</div>
		</div>
	  {this.state.menuJobSelected &&
		<div className="fluxPopupMenu" onMouseLeave={(event) => this.hideJobPopup()}>
			<table className="fluxPopupMenu" >
				<tbody >
					<tr className="fluxPopupMenuItem">
						<td className="fluxPopupMenuItem" onMouseDown={(event) =>this.props.newJob(event)}>New</td>
						<td className="mxPopupMenuItem"
							style={tdStyle}></td>
					</tr>
					<tr className="fluxPopupMenuItem">
						<td className="fluxPopupMenuItem" onMouseDown={(event) =>this.props.prepareOpenJobSelector()}>Open...</td>
						<td className="mxPopupMenuItem"
							style={tdStyle}></td>
					</tr>
					<tr className="fluxPopupMenuItem">
						<td className="fluxPopupMenuItem" onMouseDown={(event) =>this.saveJob()}>Save  </td>
						<td className="mxPopupMenuItem"
							style={tdStyle}></td>
					</tr>
					<tr className="fluxPopupMenuItem">
						<td className="fluxPopupMenuItem" onMouseDown={(event) =>this.openSaveAsDialog()}>Save as...</td>
						<td className="mxPopupMenuItem"
							style={tdStyle}></td>
					</tr>
					<tr className="fluxPopupMenuItem">
						<td className="fluxPopupMenuItem" onMouseDown={(event) =>this.openRenameDialog()}>Rename...</td>
						<td className="mxPopupMenuItem"
							style={tdStyle}></td>
					</tr>
				</tbody>
			</table>
		</div>


	  }
	  {this.state.saveAsDialogOpened  &&
		<ChangeNameDialog label="Save Job as:" changeName={(newName)=>this.saveAs(newName)} closeDialog={()=>this.closeSaveAsDialog()} oldName={this.props.jobName}/>
	  }
	  {this.state.renameDialogOpened  &&
		<ChangeNameDialog label="Rename Job:" changeName={(newName)=>this.renameJob(newName)} closeDialog={()=>this.closeRenameDialog()} oldName={this.props.jobName}/>
	  }
      </header>


    );
  }
}

export default HeaderContainer;
