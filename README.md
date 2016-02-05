# photosorter
A command line tool for sorting photos to folders based on the time interval in which they were taken.

Building with gradle:
gradle buildNeeded installDist

Executable then can be found below 'build/install/photosorter/bin'


usage: PhotoSorter
 -dfe <arg>           Java date format for event folders.
 -dsplitchar <arg>    This character splits the date string from the rest
 of the filename.
 -h,--help            Print this message.
 -minhours <arg>      A new event is assumed when more than these hours
 have passed.
 -p,--path <arg>      Path to photos. If not specified the current working
 directory is used.
 -timezone <arg>      The timezone of the photos and events.
 'Europe/Berlin' for instance. If not specified the
 system timezone is assumed.
 -w,--write-changes   Write changes. Otherwise no changes are written!
