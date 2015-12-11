# Food4thought Installation Guide
First you must install Android Studio on your computer
	1. Go to: http://developer.android.com/sdk/installing/studio.html
	2. In case you dont have an Android SDK installed you can go to:
			http://developer.android.com/sdk/installing/index.html
	3. Open Android Studio up and open the root project folder
	
# There are two ways you can run our app:
	1. On a real Android Device
		1. Plug your device to your computer using a USB cable.
		2. If you are running on windows you might need to install a USB driver
			- For help go to : http://developer.android.com/tools/extras/oem-usb.html
		3. In Android Studio click Run from the toolbar
		4. In the Choose Device Window that appears, select Choose a running device radio
		   button
		5. Select your device, then press OK.
	2. Using Android Studio
		1. In Android Studio, select Tools > Android > AVD Manager
		2. Click Create Virtual Device
		3. In the Select Hardware window, select device configuration, such as Nexus 6 then click Next
		4. Select the desired system version for the AVD and click Next
		5. Click Finish
		6. In Android Studio, select any folder and click Run
		7. In the Choose Device window, click the Lauch emulator radio button
		8. Select the emulator you created from the dropdown and click OK 

# Important
You may run into an OutOfMemoryException. To circumvent this, change the amount of RAM your emulator has to 2GB.
	1. Go to Tools->Android->AVD Manager
	2. Click the pencil icon on your desired emulator
	3. Click Show Advanced Settings
	4. Scroll down the Memory and Storage
