#A python script to define functions for running a server

from grl.prototype.networking import Server


def help():
	print("kick(username)")
	print("\tdisconnects the client with username")
	print("status()")
	print("\tdisplays the current server status")
	print("stop()")
	print("\tstops the server")

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
	print(status)

def stop():
	server = Server.getInstance()
	server.stop()
	print("Server stopping...")

print("Script - Server.py Loaded")