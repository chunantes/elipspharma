#!/usr/bin/perl -w
use strict;
@ARGV > 0 or print << "EOL" and exit;
usage: $0 <command> elapsed time
EOL
my ($sec,$min,$hour,$mon,$year,$wday,$yday,$isdst)=localtime(time);
printf "Debut de '@ARGV' a %s:%s:%s\n", $hour,$min,$sec;
system(@ARGV);
my ($sec2,$min2,$hour2,$mon2,$year2,$wday2,$yday2,$isdst2)=localtime(time);
printf "Fin de '@ARGV' a %s:%s:%s\n", $hour2,$min2,$sec2;
