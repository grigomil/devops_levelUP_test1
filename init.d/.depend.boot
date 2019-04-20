TARGETS = console-setup resolvconf mountkernfs.sh ufw hostname.sh screen-cleanup x11-common plymouth-log apparmor udev keyboard-setup cryptdisks cryptdisks-early networking hwclock.sh mountdevsubfs.sh checkroot.sh lvm2 checkfs.sh urandom iscsid open-iscsi mountall.sh checkroot-bootclean.sh mountnfs-bootclean.sh mountnfs.sh bootmisc.sh kmod mountall-bootclean.sh procps
INTERACTIVE = console-setup udev keyboard-setup cryptdisks cryptdisks-early checkroot.sh checkfs.sh
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
cryptdisks: checkroot.sh cryptdisks-early udev lvm2
cryptdisks-early: checkroot.sh udev
networking: resolvconf mountkernfs.sh urandom procps
hwclock.sh: mountdevsubfs.sh
mountdevsubfs.sh: mountkernfs.sh udev
checkroot.sh: hwclock.sh mountdevsubfs.sh hostname.sh keyboard-setup
lvm2: cryptdisks-early mountdevsubfs.sh udev
checkfs.sh: cryptdisks lvm2 checkroot.sh
urandom: hwclock.sh
iscsid: networking
open-iscsi: networking iscsid
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
checkroot-bootclean.sh: checkroot.sh
mountnfs-bootclean.sh: mountnfs.sh
mountnfs.sh: networking
bootmisc.sh: mountnfs-bootclean.sh checkroot-bootclean.sh mountall-bootclean.sh udev
kmod: checkroot.sh
mountall-bootclean.sh: mountall.sh
procps: mountkernfs.sh udev
