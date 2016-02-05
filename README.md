# photosorter
A command line tool for sorting photos to folders based on the time interval in which they were taken. It uses the exif information of the images and thus can handle all files with meta information. JPG and most raw files are definitely supported. PNG and other images with meta information should work, too.

Building is fone with gradle:
gradle buildNeeded installDist

Executable then can be found below 'build/install/photosorter/bin'

'photosorter -h' shows the help.
