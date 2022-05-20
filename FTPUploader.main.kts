#!/usr/bin/env kotlin

@file:DependsOn("D:\\_Prog\\Kotlin\\MobWars\\ftp4j-1.7.2.jar")

import it.sauronsoftware.ftp4j.FTPClient
import java.io.File

val client = FTPClient()
client.connect("gra-adv4-80.server.pro", 21)
client.login("40787", "FnbXBNfegQkn7iP")

val pluginJarFile = File("D:\\_Prog\\Kotlin\\MobWars\\target\\MobWars-0.1a.jar")
client.changeDirectory("plugins")
client.upload(pluginJarFile)

client.disconnect(true)