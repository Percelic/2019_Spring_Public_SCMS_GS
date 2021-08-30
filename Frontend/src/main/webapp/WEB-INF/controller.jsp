
<%@page import="java.net.InetAddress"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Controller UI </title>
</head>

<script language="javascript" type="text/javascript">
	
	var server_ExtIP = "<%=request.getServerName()%>";
	var server_IntIP = "<%=InetAddress.getLocalHost().getHostAddress()%>";
	
	//var addr = server_ExtIP == "localhost" ? server_IntIP + ":5100" : server_ExtIP + ":5100";
	var addr = "192.168.0.251:7100"			// test server
	
	var Uri = "ws://" + addr;
	
	var output;
	
	var intervalObj1;

	function init() {
	
		output = document.getElementById("output");
		init_WebSocket();
	}
	
	function init_WebSocket() {
		webSocket = new WebSocket(Uri);
		
		webSocket.onopen = function(event) {
			onOpen(event);
		}
		
		webSocket.onmessage = function(event) {
			onMessage(event);
		}	
	}
	
	function onOpen(evt) {
		writeToScreen(addr + " 트래커 서버에 연결되었습니다.");
		//doSend("Test Message");
	}
	
	function onClose(evt) {
		writeToScreen("트래커 제어 서버 연결이 종료되었습니다.");
	}
	
	function onMessage(evt) {
		writeToScreen('<span style="color: blue;"> 수신 : ' + evt.data + '</span>');
		//webSocket.close();
	}
	
	function onError(evt) {
		writeToScreen('<span style="color: red;">에러:</span> ' + evt.data);
	}
	
	function doSend(message) {
		writeToScreen("발신  : " + message);
		webSocket.send(message);
	}
	
	function mouseupMessage() {
		var _szMsg = document.getElementById('sel_GID').value + ',' + document.getElementById('sel_TID').value + ',' + '0,h' + ',' + '1,0' + ',' + '1,0';
		clearInterval(intervalObj1);
		
		return _szMsg;
	}
	
	function mousedownMessage(dir) {
		var _szMsg = "";
		
		switch(dir) {
		case 'up':
			_szMsg = document.getElementById('sel_GID').value + ',' + document.getElementById('sel_TID').value + ',' + '0,h' + ',' + '1,1' + ',' + '0,0';
			break;
		case 'down':
			_szMsg = document.getElementById('sel_GID').value + ',' + document.getElementById('sel_TID').value + ',' + '0,h' + ',' + '1,2' + ',' + '0,0';
			break;
		case 'left':
			_szMsg = document.getElementById('sel_GID').value + ',' + document.getElementById('sel_TID').value + ',' + '0,h' + ',' + '0,0' + ',' + '1,1';
			break;
		case 'right':
			_szMsg = document.getElementById('sel_GID').value + ',' + document.getElementById('sel_TID').value + ',' + '0,h' + ',' + '0,0' + ',' + '1,2';
			break;
		}
		
		return _szMsg;
	}
	
	function interval_send(msg,interval) {
		doSend(msg);
		
		intervalObj1 = setInterval(function() {
			doSend(msg);
		},interval);
	}
	
	function writeToScreen(message) {
		var pre = document.createElement("p");
		pre.style.wordWrap = "break-word";
		pre.innerHTML = message;
		output.appendChild(pre);
	}
	
	window.addEventListener("load",init, false);
	
	window.onbeforeunload = function() {
		webSocket.onclose = function() {};
		webSocket.close();
	};
</script>

<body>
	<h2>WebSocket Client - TCP Server Connection Test</h2>
	* 메시지 전송 여부는 [F12] - Network - Name 탭 - Server IP - 우측 Frames 탭으로 확인
	
	<div id="controller">
	<p>
	<div>
	군집 ID : 
	<select id="sel_GID">
		<option selected>18</option>
		<option>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>	
		<option>6</option>
		<option>7</option>
		<option>8</option>
		<option>9</option>
		<option>10</option>
		<option>15</option>	
		<option>16</option>
		<option>17</option>
	</select>
	<p>
	트래커 ID : 
	<select id="sel_TID">
		<option>0</option>
		<option selected>1</option>
		<option>2</option>
		<option>3</option>
		<option>4</option>
		<option>5</option>
		<option>6</option>
		<option>7</option>
		<option>8</option>
		<option>9</option>
		<option>10</option>
		<option>11</option>
		<option>12</option>
		<option>13</option>
		<option>14</option>
		<option>15</option>
		<option>16</option>
		<option>17</option>
		<option>18</option>
		<option>19</option>
		<option>20</option>
		<option>51</option>
		<option>52</option>
		<option>53</option>
		<option>54</option>
		<option>55</option>
		<option>56</option>
		<option>57</option>
		<option>58</option>
		<option>59</option>
		<option>60</option>
		<option>71</option>
		<option>72</option>
		<option>73</option>
		<option>74</option>
		<option>75</option>
		<option>76</option>
		<option>77</option>
		<option>78</option>
		<option>79</option>
		<option>80</option>
	</select>
	
	<p>
	<button id="btn_up" onmouseup="doSend(mouseupMessage())" onmousedown="interval_send(mousedownMessage('up') ,5000)" style="margin: 0px 0px 0px 25px;" >상향</button>  
	<p>
	<button id="btn_left" onmouseup="doSend(mouseupMessage())" onmousedown="interval_send(mousedownMessage('left'),5000)">좌향</button>    
	<button id="btn_right" onmouseup="doSend(mouseupMessage())" onmousedown="interval_send(mousedownMessage('right'),5000)">우향</button>
	<button id="btn_stop" onclick="doSend(mouseupMessage())" style="margin: 0px 0px 0px 25px;">정지</button>
	<button id="btn_close" onclick="webSocket.close()">소켓종료</button>
	<p>
	<button id="btn_down" onmouseup="doSend(mouseupMessage())" onmousedown="interval_send(mousedownMessage('down'),5000)" style="margin: 0px 0px 0px 25px;">하향</button>
	<p>
	<button id="btn_modeoff"  onClick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',' + '1,h' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;">모드 제어 OFF</button>
	<button id="btn_shadowmode" onClick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',' + '1,H' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;">음영 제어 ON</button>
	<button id="btn_snowmode" onClick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',' + '1,S' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;">SNOW 모드 ON</button>
	<button id="btn_windmode" onClick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',' + '1,W' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;">WIND 모드 ON</button>
	<button id="btn_nightmode" onClick="doSend(getElementById('sel_GID').value + ',' + getElementById('sel_TID').value + ',' + '1,N' + ',' + '0,0,0,0')" style="margin: 0px 0px 0px 25px;">NIGHT 모드 ON</button>	
		
	<!--  <select id="sel_Alg">
		<option value="h">해제</option>
		<option value="H">음영제어모드</option>
		<option value='S'>적설모드</option>
		<option value='W'>바람모드</option>
		<option value='N'>야간모드</option>
	</select>
	-->
	</div>
	<p>
	<input id="input_send"/>
	<button id="btn_send" onclick="doSend(getElementById('input_send').value)">전송</button>
	</div>
	<div id="output"></div>
</body>
</html>