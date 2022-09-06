---
title: Getting Started
parent: Tutorial
---
# Getting Started

To use CMLC, the following projects are required to be installed.
 - [CE Toolchain](https://github.com/CE-Programming/toolchain)
 - [Cedar](https://github.com/cailyn-baksh/cedar)

Additionally, this tutorial assumes that you are using a Unix-like system with
[GNU Make](https://www.gnu.org/software/make/) installed. Some modification may
be necessary to adapt this tutorial to other operating or build systems.

## Setting up a project

CMLC is intended to be used alongside the CE Toolchain. Thus, projects using
CML use the [same basic project structure](https://ce-programming.github.io/toolchain/static/getting-started.html#project-structure).

On top of this structure, an additional folder called `gui` should be created
at the project root. Note that this structure is not required for CMLC to work;
instead it is a suggestion which will be used by this tutorial.

## Creating the makefile
The following makefile, modified from the basic CE Toolchain makefile, will
compile your GUIs into your TI-84+ CE programs. This makefile can be modified
in any way as described in the [CE Toolchain docs](https://ce-programming.github.io/toolchain/static/makefile-options.html).

```make
# ----------------------------
# Makefile Options
# ----------------------------

NAME = DEMO
ICON = icon.png
DESCRIPTION = "CMLC Demo"
COMPRESSED = NO
ARCHIVED = NO

CML_FILES = $(wildcard gui/*.cml)
DEPS = gui

CFLAGS = -Wall -Wextra -Oz
CXXFLAGS = Wall -Wextra -Oz

# ----------------------------

include $(shell cedev-config --makefile)

gui: $(CML_FILES)

gui/%.cml:
	cmlc $@ -o src/gui/
```

## Creating the gui
Next, we should create the gui. Start by creating a file in `gui` called
`main.cml`.

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<cedarml version="1.0">
    
</cedarml>
```

Add a new window called `main` with an event handler called
`mainWindowHandler`. Give this window a body, which for now will be empty.
```xml
<window name="main" handler="mainWindowHandler">
    <body>

    </body>
</window>
```

Then, add a label with the text `Hello World!`
```xml
<label id="LBL_HELLO" x="10" y="10" width="50" height="20">Hello World!</label>
```

The final CML file should be:
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<cedarml version="1.0">
    <window name="main" handler="mainWindowHandler">
        <body>
            <label id="LBL_HELLO" x="10" y="10" width="50" height="20">Hello World!</label>
        </body>
    </window>
</cedarml>
```

## Writing the C

Create a file in `src` called `ids.h`. This is the header file which defines
all the GUI IDs. Define the ID for the hello label here. Remember that IDs in
Cedar must be greater than 0.

```c
#ifndef _IDS_H_
#define _IDS_H_

#define LBL_HELLO 1

#endif  // _IDS_H_
```

Next, create another file in the `src` folder called `main.c`. Start this file
by including the necessary header files.

```c
#include <cedar.h>

#include "ids.h"
#include "gui/main.h"
```

Add an event handler for the main window. Since this window doesn't do
anything, the event handler will be empty.
```c
CALLBACKRESULT mainWindowHandler(CedarWindow *self, EVENT event, uint24_t param) {

}
```

Finally, add the main function. In `main` you will initialize Cedar, call the
display function for your window, and then clean up Cedar.
```c
int main() {
    cedar_Init();

    displayMainWindow();

    cedar_Cleanup();
    return 0;
}
```

The final C file should be:
```c
#include <cedar.h>

#include "ids.h"
#include "gui/main.h"

CALLBACKRESULT mainWindowHandler(CedarWindow *self, EVENT event, uint24_t param) {

}

int main() {
    cedar_Init();

    displayMainWindow();

    cedar_Cleanup();
    return 0;
}
```
