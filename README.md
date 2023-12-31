# Trace Compass - Kernel Analysis Plugin for System Optimization

## Table of Contents

- [Trace Compass Kernel Analysis Plugin](#trace-compass-kernel-analysis-plugin)
- [LTTng and Trace Compass Setup](#lttng-and-trace-compass-setup)
- [References](#references)


## Trace Compass Kernel Analysis Plugin

### Introduction
This project uses Trace Compass to run an analysis on a given trace. This project aims to dump the selected fields of the trace into a formatted file, which will then be used on a k-means clustering algorithm for further analysis and system optimization. The development plan is to implement the machine learning algorithm within the plugin. 

### Requirements
- Ubuntu 22.04
- Virtual Box
- LTTng
- Eclipse IDE for Committers (Include the plug-in development environment)

### Usage
- In the current state, this plugin outputs a simple message and the names of the fields that are supposed to be analyzed by the plugin. 
      
## LTTng and Trace Compass Setup

### Prerequisites

- Ubuntu 22.04
- VirtualBox


1. **Download Ubuntu 22.04:**
   Download the Ubuntu 22.04 LTS image from [this link](https://ubuntu.com/download/desktop/thank-you?version=22.04.3&architecture=amd64).

2. **Install VirtualBox:**
   Follow the instructions on the [VirtualBox website](https://www.virtualbox.org/wiki/Downloads) to install VirtualBox.

3. **Create a VirtualBox virtual machine with Ubuntu 22.04:**
   Use the downloaded Ubuntu image to create a virtual machine inside VirtualBox.
   Follow the instructions on [this link](https://ubuntu.com/tutorials/how-to-run-ubuntu-desktop-on-a-virtual-machine-using-virtualbox#1-overview).
   
### Installation

1. ### Install LTTng:
   1. **Install Git:** For clone LTTng repository from GitHub
      Open a terminal inside the virtual machine and run:
      ```bash
      $ sudo apt-get update
      $ sudo apt-get install git
      ```

   2. **Installing Nautilus Admin Extension:**
      For open root files by using File Explorer:
      ```bash
      $ sudo apt-get install nautilus-admin
      $ nautilus -q
      ```
   3. **Installing LTTng from Source:**
      For detailed instructions on installing LTTng from source, take a look at this [guide by IBM](https://www.ibm.com/support/pages/howto-tracing-lttng).
      ```bash
      sudo apt-get install autoconf automake libtool pkg-config
      ```
      ```bash
      # Cloning LTTng source code
      $ git clone git://git.lttng.org/lttng-tools.git
      $ git clone git://git.lttng.org/lttng-modules.git
      $ git clone git://git.lttng.org/lttng-ust.git
      $ git clone git://git.lttng.org/userspace-rcu.git
      ```
      ```bash
      # Buiding and installing liburcu library
      $ cd userspace-rcu
      $ ./bootstrap
      $ ./configure
      $ make
      $ make install
      $ sudo ldconfig
      ```
      ```bash
      # Bulding and installing lttng-ust
      $ cd lttng-ust
      $ ./bootstrap
      $ ./configure
      $ make
      $ make install
      $ ldconfig
      ```
      ```bash
      # Building and installing lttng-tools
      $ cd lttng-tools
      $ ./bootstrap
      $ ./configure
      $ make
      $ make install
      $ ldconfig
      ```
      ```bash
      # Building and installing lttng-modules
      $ cd lttng-modules
      $ make
      $ sudo make modules_install
      $ sudo depmod -a
      ```
      ```bash
      # Check LTTng version
      $ lttng --version
      ```
2. **Run your first LTTng trace:**
   ```bash
   $ lttng create #create an auto lttng session
   $ lttng enable-event -k -a #enable kernal
   $ lttng start #start tracing
   $ lttng stop #stop tracing
   $ lttng destroy #lttng destroy session. This step WILL NOT destroy the lttng tracing data.
   ```
3. **Print your result by using Babeltrace2:**
   ```bash
    $ babeltrace2 /root/lttng-traces/auto-xxxxxxxx - xxxxxx #change this directory to your trace data file location
    ```
4. **Possible error: Kernel tracer not available**\
   Reinstall lttng-modules
   ```bash
   $ cd lttng-modules
   $ make
   $ sudo make modules_install
   $ sudo depmod -a
   ```
5. ### Installing Trace Compass on Ubuntu 
   Trace Compass/Development Environment Setup Guide follow [this website](https://wiki.eclipse.org/Trace_Compass/Development_Environment_Setup).

   1. **Download the latest Trace Compass from the official website:**
      You can find the latest Trace Compass releases [here](https://eclipse.dev/tracecompass/). 
      
   2. **Extract the downloaded archive:**
      Extract the downloaded tar.gz file to a location of your choice.
Or run following on commend line:
       ```bash
       $ tar xzvf eclipse-committers-2023-03-R-linux-gtk-x86_64.tar.gz 
      ```
   3. **Install required dependencies:**
      Trace Compass uses source compatibility to Java 11. However, to run the Trace Compass RCP and to develop it, Java 17 is required. Here is how to install Java 17 on recent Ubuntu:
      ```bash
      $ sudo apt-get install openjdk-17-jdk

6. ### Trace Compass Instruction
   Learn Trace Compass Main Features and how to import traces to the project from [this website](https://archive.eclipse.org/tracecompass/doc/stable/org.eclipse.tracecompass.doc.user/Trace-Compass-Main-Features.html#Project_Explorer_View).
   



## References
https://releases.ubuntu.com/18.04/ \
https://www.virtualbox.org/wiki/Downloads \
https://ubuntu.com/tutorials/how-to-run-ubuntu-desktop-on-a-virtual-machine-using-virtualbox#1-overview \
https://www.ibm.com/support/pages/howto-tracing-lttng \
https://lttng.org/docs/v2.13/ \
https://github.com/tracecompass/tracecompass \
https://tracingsummit.org/ts/2019/files/Tracingsummit2019-babeltrace2-marchi.pdf \
https://ardupilot.org/dev/docs/using-linux-trace-toolkit-ng-lttng-to-trace-ardupilot-in-realtime.html# \
https://www.efficios.com/pub/lfcs2013/collab-2013-slides.pdf \
https://tracingsummit.org/ts/2014/files/Tracingsummit2014-gbastien.pdf \
https://eclipse.dev/tracecompass/ \
https://wiki.eclipse.org/Trace_Compass/Development_Environment_Setup \
https://archive.eclipse.org/tracecompass/doc/stable/org.eclipse.tracecompass.doc.user/Trace-Compass-Main-Features.html#Project_Explorer_View
https://github.com/tx13al/trace_compass_rl_model?tab=readme-ov-file#reinforcement-learning-for-system-calls-analysis
https://github.com/tuxology/tracevizlab
https://www.simplilearn.com/tutorials/machine-learning-tutorial/k-means-clustering-algorithm#:~:text=K%2DMeans%20clustering%20is%20an,objects%20belonging%20to%20another%20cluster.
https://www.youtube.com/watch?v=_aWzGGNrcic&ab_channel=VictorLavrenko
https://granulate.io/blog/performance-optimization-the-basics-metrics-and-whats-wrong/

