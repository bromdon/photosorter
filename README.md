<h1>Photosorter</h1>
A command line tool for sorting photos to folders based on the time interval in which they were taken. 
It uses the EXIF information of the images and thus can handle all files with meta information. 
JPG and most raw files are definitely supported. PNG and other images with meta information should work, too.

<h2>Requirements:</h2>
Java 8 or higher<br />
Windows or Unix/Linux<br />
Image files with Time meta information or same naming scheme as image files. (e.g. XMP).<br />
Commandline<br/>

<h2>Starting Photosorter</h2>
Zip and tar distribution files with all necessary files can be found in build/distributions. An already extracted distribution can be found in build/install with a batch / sh file below bin/.

<h2>Photosorter command line arguments:</h2>
All arguments are optional and contain sensible defaults.

<pre>usage: PhotoSorter
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
</pre>

<h2>Building with gradle:</h2>
'gradle buildNeeded installDist'

Executable then can be found below 'build/install/photosorter/bin'
<pre>
'photosorter -h' in the bin directory shows the help.
</pre>


<h2>Examples:</h2>

<h3>Before (flat photo files in folder):</h3>
<pre>
2014-12-01T1451_06371.arw
2014-12-01T1451_06371.jpg
2014-12-01T1453_06374.arw
2014-12-01T1453_06374.arw.xmp
2014-12-01T1453_06374.jpg
2014-12-01T1455_06375.arw
2014-12-01T1455_06375.arw.xmp
2014-12-01T1455_06375.jpg
2014-12-01T1456_06376.arw
2014-12-01T1456_06376.arw.xmp
2014-12-01T1456_06376.jpg
2014-12-01T1510_06377.arw
2014-12-01T1510_06377.arw.xmp
2014-12-01T1510_06377.jpg
2014-12-01T1510_06378.arw
2014-12-01T1510_06378.arw.xmp
2014-12-01T1510_06378.jpg
2014-12-01T1510_06379.arw
2014-12-01T1510_06379.jpg
2014-12-01T1512_06380.arw
2014-12-01T1512_06380.arw.xmp
2014-12-01T1512_06380.jpg
2014-12-01T1515_06381.arw
2014-12-01T1515_06381.arw.xmp
2014-12-01T1515_06381.jpg
2014-12-01T1516_06382.arw
2014-12-01T1516_06382.arw.xmp
2014-12-01T1516_06382.jpg
2014-12-01T1520_06383.arw
2014-12-01T1520_06383.arw.xmp
2014-12-01T1520_06383.jpg
2014-12-01T1522_06384.arw
2014-12-01T1522_06384.arw.xmp
2014-12-01T1522_06384.jpg
2014-12-21T2029_06385.arw
2014-12-21T2029_06385.jpg
2014-12-21T2031_06387.arw
2014-12-21T2031_06387.arw.xmp
2014-12-21T2031_06387.jpg
2014-12-21T2031_06388.arw
2014-12-21T2031_06388.jpg
2014-12-24T1821_06391.arw
2014-12-24T1821_06391.arw.xmp
2014-12-24T1821_06391.jpg
2014-12-24T1821_06392.arw
2014-12-24T1821_06392.jpg
2014-12-24T1822_06393.arw
2014-12-24T1822_06393.arw.xmp
2014-12-24T1822_06393.jpg
2014-12-24T1822_06394.arw
2014-12-24T1822_06394.arw.xmp
2014-12-24T1822_06394.jpg
2014-12-24T1822_06396.arw
2014-12-24T1822_06396.arw.xmp
2014-12-24T1822_06396.jpg
2014-12-24T1823_06397.arw
2014-12-24T1823_06397.arw.xmp
2014-12-24T1823_06397.jpg
2014-12-24T1823_06398.arw
2014-12-24T1823_06398.arw.xmp
2014-12-24T1823_06398.jpg
2014-12-24T1823_06399.arw
2014-12-24T1823_06399.jpg
2014-12-24T1853_06400.arw
2014-12-24T1853_06400.arw.xmp
2014-12-24T1853_06400.jpg
2014-12-24T1853_06403.arw
2014-12-24T1853_06403.jpg
2014-12-24T1933_06405.arw
2014-12-24T1933_06405.arw.xmp
2014-12-24T1933_06405.jpg
2014-12-24T1934_06406.arw
2014-12-24T1934_06406.arw.xmp
2014-12-24T1934_06406.jpg
2014-12-24T1934_06407.arw
2014-12-24T1934_06407.jpg
2014-12-24T1938_06411.arw
2014-12-24T1938_06411.arw.xmp
2014-12-24T1938_06411.jpg
2014-12-24T1939_06412.arw
2014-12-24T1939_06412.arw.xmp
2014-12-24T1939_06412.jpg
2014-12-24T1940_06413.arw
2014-12-24T1940_06413.arw.xmp
2014-12-24T1940_06413.jpg
2014-12-24T1940_06414.arw
2014-12-24T1940_06414.arw.xmp
2014-12-24T1940_06414.jpg
2014-12-24T1940_06415.arw
2014-12-24T1940_06415.jpg
2014-12-24T2121_06417.arw
2014-12-24T2121_06417.jpg
2014-12-24T2121_06418.arw
2014-12-24T2121_06418.arw.xmp
2014-12-24T2121_06418.jpg
2014-12-24T2121_06419.arw
2014-12-24T2121_06419.jpg
2014-12-24T2121_06420.arw
2014-12-24T2121_06420.jpg
2014-12-24T2151_06421.arw
2014-12-24T2151_06421.arw.xmp
2014-12-24T2151_06421.jpg
2014-12-24T2152_06422.arw
2014-12-24T2152_06422.arw.xmp
2014-12-24T2152_06422.jpg
2014-12-24T2223_06423.arw
2014-12-24T2223_06423.arw.xmp
2014-12-24T2223_06423.jpg
2014-12-28T1444_06425.arw
2014-12-28T1444_06425.arw.xmp
2014-12-28T1444_06425.jpg
2014-12-28T1444_06426.arw
2014-12-28T1444_06426.jpg
2014-12-28T1444_06427.arw
2014-12-28T1444_06427.jpg
2014-12-28T1445_06429.arw
2014-12-28T1445_06429.arw.xmp
2014-12-28T1445_06429.jpg
2014-12-28T1445_06430.arw
2014-12-28T1445_06430.jpg
2014-12-28T1445_06431.arw
2014-12-28T1445_06431.arw.xmp
2014-12-28T1445_06431.jpg
2014-12-28T1446_06432.arw
2014-12-28T1446_06432.jpg
2014-12-28T1509_06435.arw
2014-12-28T1509_06435.jpg
2014-12-28T1509_06437.arw
2014-12-28T1509_06437.arw.xmp
2014-12-28T1509_06437.jpg
2014-12-28T1516_06438.arw
2014-12-28T1516_06438.arw.xmp
2014-12-28T1516_06438.jpg
2014-12-28T1516_06439.arw
2014-12-28T1516_06439.jpg
2014-12-28T1516_06440.arw
2014-12-28T1516_06440.jpg
2014-12-28T1835_06441.arw
2014-12-28T1835_06441.arw.xmp
2014-12-28T1835_06441.jpg
2014-12-28T1835_06442.arw
2014-12-28T1835_06442.jpg
2014-12-28T1856_06443.arw
2014-12-28T1856_06443.jpg
2014-12-28T1856_06444.arw
2014-12-28T1856_06444.arw.xmp
2014-12-28T1856_06444.jpg
2014-12-28T1858_06446.arw
2014-12-28T1858_06446.arw.xmp
2014-12-28T1858_06446.jpg
2014-12-28T1858_06449.arw
2014-12-28T1858_06449.arw.xmp
2014-12-28T1858_06449.jpg
2014-12-28T1905_06453.arw
2014-12-28T1905_06453.arw.xmp
2014-12-28T1905_06453.jpg
2014-12-28T1909_06455.arw
2014-12-28T1909_06455.arw.xmp
2014-12-28T1909_06455.jpg
2014-12-28T1909_06456.arw
2014-12-28T1909_06456.jpg
2014-12-28T1909_06457.arw
2014-12-28T1909_06457.jpg
2014-12-28T1936_06460.arw
2014-12-28T1936_06460.jpg
2014-12-28T1936_06461.arw
2014-12-28T1936_06461.jpg
2014-12-28T1936_06462.arw
2014-12-28T1936_06462.jpg
2014-12-28T1936_06463.arw
2014-12-28T1936_06463.arw.xmp
2014-12-28T1936_06463.jpg
2014-12-28T1936_06465.arw
2014-12-28T1936_06465.jpg
</pre>

<h3>After (Folders have the starting date of the first photo and contain respecting files):</h3>
<pre>
2014-12-01-NEW_EVENT
2014-12-21-NEW_EVENT
2014-12-24-NEW_EVENT
2014-12-28-NEW_EVENT
</pre>
