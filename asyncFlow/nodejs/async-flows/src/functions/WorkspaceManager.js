




let lastElementPosition = [100, 700];
let observer = null;

function emitChange() {
  observer(lastElementPosition);
}

export function deepCloneObject(obj) {
  return JSON.parse(JSON.stringify(obj));
}
export function observe(o) {
  if (observer) {
    throw new Error('Multiple observers not implemented.');
  }

  observer = o;
  emitChange();
}


export function canMoveFormElement(toX, toY) {
  const [x, y] = lastElementPosition;
  const dx = toX - x;
  const dy = toY - y;

  return (Math.abs(dx) < 100 && Math.abs(dy) < 100);
}

export function moveFormElement(toX, toY) {
  lastElementPosition = [toX, toY];
  emitChange();
}


export function	getMousePosition(event,element) {
  // console.log("getMousePosition");
  // console.log(event);
  // console.log(element);
  var scrollOffset=null;
	if (element!=null)  {
		scrollOffset={
			left:element.scrollLeft,
			top:element.scrollTop }
	} else {
		scrollOffset={
			left:0,
			top:0	}
	}
	return {x:event.clientX+scrollOffset.left,y:event.clientY+scrollOffset.top}
}


export function	getAbsolutePosition(myElement) {
		var y = 0, x = 0;
		var element=myElement;
		do {
			if (element.tagName==="svg") {
				var result=element.getBoundingClientRect();
				y += result.top  || 0;
				x += result.left || 0;
				element=element.parentNode;
			} else {
				y += element.offsetTop  || 0;
				x += element.offsetLeft || 0;
				element = element.offsetParent;
			}
		} while(element);

		return {
			y: y,
			x: x
		};

















	}

  export function isInBottom(id) {
  		var element = document.getElementById(id).parentElement;
      return element.scrollHeight-element.clientHeight<=element.scrollTop;
  }

	export function	gotoBottom(id){
		var element = document.getElementById(id).parentElement;
		element.scrollTop = element.scrollHeight - element.clientHeight;
	}

export function	getCanvasContainerElement() {
		return document.getElementById("canvasContainer");
	}

export function		getWorkspaceContainerElement() {
		return document.getElementById("workspace");
	}

export function		getSideBarContainerElement() {
		var result=document.getElementById("sideBarContainer");
		return result;
	}

export function		getSideBarScrollPosition() {
		var sideBarElement=getSideBarContainerElement();
			return {
				left:sideBarElement.scrollLeft,
				top:sideBarElement.scrollTop
			};

	}













