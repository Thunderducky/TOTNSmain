#A python script to define functions for running a server

from grl.prototype.networking import Server

def kick(username):
	server = Server.getInstance()
	if (server.clientConnected(username)):
		print("Kicking: "+username)
		server.disconnectClient(username)
	else:
		print("No client: "+username)
	

def status():
	server = Server.getInstance()
	status = server.getStatus()
	print("== Server Status ==")
	print(status);

print("Script - Server.py Loaded")