# What is EAPO Remote?
EAPO Remote is software designed to allow remote configuration of the EqualizerAPO application's config.txt file.

This application was developed thinking about a cheap and efficient remote equalization solution for your computer, requiring only a "driver" version that will receive configuration updates and a "controller" version that will send these settings to the driver for updating.

# Why shoud I use this?
This app allows you to control your computer's equalization without touching your device directly. It's a good option if you don't want to minimize any application running on your Windows, or in small events where your computer is responsible for the projections and sounds.

If you are an audiovisual technician at events, you know that it is not always possible to position production equipment in the best place to hear what is playing, so this application can be useful if you do not have a digital table that receives signals at the distance. This is very useful for your birthday party or your church, for example.

# How do I use it?
***WARNING: It is highly recommended that you back up your config.txt file. Just copy and paste it into the same folder, your system will take care of renaming the copy automatically.***

To use this feature, you obviously need to have [EqualizerAPO](https://sourceforge.net/projects/equalizerapo/) installed on your computer. If you already have it, install the receiver driver so that it can receive configuration signals and update the config.txt file.

When starting the driver, look for the EqualizerAPO configuration file, which is usually installed in **C:\Program Files\EqualizerAPO\config**
>(if you have installed EAPO in its default folder, the application will open this file automatically, otherwise, look for file in the folder where EAPO was installed).

After finding the file, the driver will identify the plugins in the file and display them to you, then click "Start Server" select a port (or leave the default) click OK to start the driver server.

In the mobile app, click "Connect to a server" and enter your computer's IP and the port you chose.

Voila! Your cell phone will identify the same plugins as your computer and allow you to change the settings remotely, as long as the devices are connected to the same Wi-Fi network.
