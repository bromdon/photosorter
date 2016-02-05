# photosorter
A command line tool for sorting photos to folders based on the time interval in which they were taken. It uses the exif information of the images and thus can handle all files with meta information. JPG and most raw files are definitely supported. PNG and other images with meta information should work, too.

Requirements:
Java 8 or higher
Windows or Unix/Linux
Commandline experience

Starting:
Zip and tar distribution files with all necessary files can be found in build/distributions. An already extracted
distribution can be found in build/install with a batch / sh file below bin/.

Building with gradle:
'gradle buildNeeded installDist'

Executable then can be found below 'build/install/photosorter/bin'

'photosorter -h' in the bin directory shows the help.
