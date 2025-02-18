# Nixie + Raspberry Pi + Github

A device which displays github notificaitons count.

The hardware is based on IV-9 7-segment indicator in form of Nixie tube.  
It  is controlled by SN74HC595N 8-bit shift register.

The firmare is written in `kotlin` and uses `pi4j-core` java library.  
It sends SPI signal from Rapberry Pi to hardware device.  

<image height="400" src="media/picture.jpg"/>  <image height="400" src="media/schematic.svg"/>
