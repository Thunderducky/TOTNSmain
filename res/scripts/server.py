#A python script to define functions for running a server

from grl.prototype.networking import Server
from grl.prototype.messaging import Message
from grl.prototype.networking.server import ChatProcessor

class ServerCommands(object):
	@staticmethod
	def help():
		print("kick(username)")
		print("\tdisconnects the client with username")
		print("status()")
		print("\tdisplays the current server status")
	@staticmethod
	def kick(username):
		server = Server.getInstance()
		if (server.clientConnected(username)):
			print("Kicking: "+username)
			server.disconnectClient(username)
		else:
			print("No client: "+username)
		
	@staticmethod
	def status():
		server = Server.getInstance()
		status = server.getStatus()
		print("== Server Status ==")
		print(status)
	
	@staticmethod
	def broadcast(message):
		server = Server.getInstance()
		messageObj = ChatProcessor.createBroadcast(message)
		server.sendMessageAll(messageObj)
		print("Chat:"+message)
	
	@staticmethod
	def message(to,message):
		server = Server.getInstance()
		messageObj = Message("Chat.Message")
		messageObj.setArgument("message",message)
		messageObj.setArgument("from","Server")
		messageObj.setArgument("to",to)
		server.sendMessageTo(messageObj,to)
		print("Chat("+to+"):"+message);

print("Script - Server.py Loaded")