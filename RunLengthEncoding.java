/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
  private DList rleList = new DList();
  private int numOfRuns;
  private int rleWidth;
  private int rleHeight;
  private RunIterator iterrrrator;



  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
    // Your solution here.
    rleWidth = width;
    rleHeight = height;
    DListNode run; 
    int[] runContents = new int[4];
    runContents[0] = (int) (0);
    runContents[1] = (int) (0);
    runContents[2] = (int) (0);
    runContents[3] = width*height;
    run = new DListNode(runContents);
    rleList.head = run;
    rleList.tail = rleList.head;
    numOfRuns = rleList.size;
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
    // Your solution here.
    rleWidth = width;
    rleHeight = height;
    int totalLength = width*height;
    int tracker = 0;
    int i = 0;
    int[] runContents = new int[4];
    while (tracker < totalLength) {
      runContents[0] = red[i];
      runContents[1] = green[i];
      runContents[2] = blue[i];
      runContents[3] = runLengths[i];
      //System.out.print("(");
      //System.out.print(runContents[0]);
      //System.out.print(runContents[1]);
      //System.out.print(runContents[2]);
      //System.out.print(runContents[3]);
      //System.out.println(")");
      rleList.insertEnd(runContents);
      //System.out.println(rleList);
      tracker = tracker+runLengths[i];
      i++;
    }
    //runContents[0] = red[i-1];
    //runContents[1] = green[i-1];
    //runContents[2] = blue[i-1];
    //runContents[3] = runLengths[i-1];
    //rleList.insertEnd(runContents);
    //System.out.println(rleList);

    numOfRuns = rleList.size;
  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return rleWidth;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return rleHeight;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this rleList.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    // Replace the following line with your solution.
    RunIterator iteRator = new RunIterator(rleList);
    return iteRator;
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
    iterrrrator = this.iterator();
    PixImage unRLEPixImage = new PixImage(this.getWidth(), this.getHeight());
    int[] runArray = new int[4];
    int x = 0;
    int y = 0;
    int k = 0;//check how many pixels we have put in the piximage.
    while (y<unRLEPixImage.getHeight()) {
      while (x<unRLEPixImage.getWidth()) {
        if (runArray[0]==0) {
            runArray = iterrrrator.next();
            //System.out.print("(");
            //System.out.print(runArray[0]);
            //System.out.print(runArray[1]);
            //System.out.print(runArray[2]);
            //System.out.println(")");
        }
        runArray[0] = runArray[0] - 1;
        unRLEPixImage.setPixel(x, y, (short) (runArray[1]), (short) (runArray[2]), (short) (runArray[3]));
        //System.out.println(runArray[0]);   
        x++;
      }
      x = 0;
      y++;
    }
    return unRLEPixImage;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.
    String result = "[ ";
    DListNode current = rleList.head;
    while (current != null) {
      result = result + "(" + current.redIntensity + "," + current.greenIntensity + "," + current.blueIntensity + "," + current.lengthOfRun + ")" + " ";
      current = current.next;
    }
    return result + "]";
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
    // Your solution here, but you should probably leave the following line
    // at the end.
    rleWidth = image.getWidth();
    rleHeight = image.getHeight();
    DList rleImage = new DList();
    int lengthOfRunTracker = 0;
    int numOfRunTracker = 0;
    int[] trackerArray = new int[4];
    trackerArray[3] = lengthOfRunTracker;
    trackerArray[0] = -1;
    trackerArray[1] = -1;
    trackerArray[2] = -1;
    for (int y=0; y<image.getHeight(); y++) {
      for (int x=0; x<image.getWidth(); x++) {
        if ((int) (image.getRed(x, y)) == trackerArray[0] && 
            (int) (image.getGreen(x, y)) == trackerArray[1] && 
            (int) (image.getBlue(x, y)) == trackerArray[2]) {
          trackerArray[3]++;
        }else {
          rleImage.insertEnd(trackerArray);
          int[] newArray = new int[4];
          newArray[3] = 1;
          newArray[0] = (int) (image.getRed(x, y));
          newArray[1] = (int) (image.getGreen(x, y));
          newArray[2] = (int) (image.getBlue(x, y));
          trackerArray = newArray;
          numOfRunTracker++;
        }
      }
    }
    rleImage.insertEnd(trackerArray);
    rleImage.removeFront();
    rleList = rleImage;
    numOfRuns = numOfRunTracker;
    //System.out.println(rleList);
    check();
  }



  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
    // Your solution here.
    DListNode referenceNode = new DListNode();
    referenceNode = rleList.head;
    int totalLengthChecker = referenceNode.lengthOfRun;
    while (referenceNode.next != null) {
      //System.out.println(referenceNode);
      
      if (referenceNode.lengthOfRun < 1) {
        System.out.println("No RUN's length should be less than 1");
      }
      if (referenceNode.next.equals(referenceNode)) {
        System.out.println("bad RLE");
      }else {
        //System.out.println(totalLengthChecker);
        referenceNode = referenceNode.next;
        totalLengthChecker = totalLengthChecker + referenceNode.lengthOfRun;
      }
      if (referenceNode == rleList.tail) {
        break;
      }
    }
    //System.out.println(referenceNode);
    //System.out.println(totalLengthChecker);
    if (totalLengthChecker != (rleWidth*rleHeight)) {
      System.out.println("Total Length is " + totalLengthChecker + ", something is wrong with the HEIGHT and WIDTH");
    }
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
    //System.out.println(rleList);
    int pixelLocator;
    pixelLocator = y*rleWidth + x + 1;
    //System.out.println(rleWidth);
    //System.out.println(pixelLocator);
    int targetNode;
    DListNode currentNode = rleList.head;
    //System.out.println(rleList.head);
    int[] newNodeList = new int[4];
    newNodeList[0] = red;
    newNodeList[1] = green;
    newNodeList[2] = blue;
    newNodeList[3] = 1;
    DListNode added = new DListNode(newNodeList);

    while (pixelLocator>currentNode.lengthOfRun) {
      pixelLocator = pixelLocator - currentNode.lengthOfRun;
      currentNode = currentNode.next;
    }
    //System.out.println("current node is: "+currentNode);
    //System.out.println("position of the pixelLocator: "+pixelLocator);
    //System.out.println("length of current node: "+currentNode.lengthOfRun);
    if (currentNode.redIntensity == red && currentNode.greenIntensity == green && currentNode.blueIntensity == blue) {
      return;
    }
    if (pixelLocator==1) {
      if (currentNode.prev != null) {
        if (currentNode.prev.redIntensity == red && currentNode.prev.greenIntensity == green && currentNode.prev.blueIntensity == blue) {
          if (currentNode.lengthOfRun > 1) {
            currentNode.lengthOfRun--;
            currentNode.prev.lengthOfRun++;
          }else if (currentNode.lengthOfRun == 1) {
            if (currentNode.next == null) {
              currentNode.prev.lengthOfRun++;
              rleList.tail = currentNode.prev;
            }else if (currentNode.next.redIntensity == red && currentNode.next.greenIntensity == green && currentNode.next.blueIntensity == blue) {
              currentNode.prev.lengthOfRun = (short) (currentNode.prev.lengthOfRun + currentNode.next.lengthOfRun + 1);
              currentNode.prev.next = currentNode.next.next;
              currentNode.next.next.prev = currentNode.prev;
            }else {
              currentNode.prev.lengthOfRun++;
              currentNode.prev.next = currentNode.next;
              currentNode.next.prev = currentNode.prev;
            }        
          } 
        }else {
          if (currentNode.lengthOfRun > 1) {
            currentNode.prev.next = added;
            added.prev = currentNode.prev;
            currentNode.lengthOfRun--;
            added.next = currentNode;
            currentNode.prev = added;
          }else if (currentNode.lengthOfRun == 1) {
            if (currentNode.next == null) {
              currentNode.prev.next = added;
              added.prev = currentNode.prev;
              rleList.tail = added;
            }else if (currentNode.next.redIntensity == red && currentNode.next.greenIntensity == green && currentNode.next.blueIntensity == blue) {
              currentNode.next.lengthOfRun++;
              currentNode.prev.next = currentNode.next;
              currentNode.next.prev = currentNode.prev;
            }else {
              currentNode.prev.next = added;
              added.prev = currentNode.prev;
              added.next = currentNode.next;
              currentNode.next.prev = added;
            }
          }
        }
      }else if (currentNode.prev == null) {
        if (currentNode.lengthOfRun > 1) {
          currentNode.lengthOfRun--;
          added.next = currentNode;
          currentNode.prev = added;
          rleList.head = added;
        }else if (currentNode.lengthOfRun == 1) {
          if (currentNode.next.redIntensity == red && currentNode.next.greenIntensity == green && currentNode.next.blueIntensity == blue) {
            currentNode.next.lengthOfRun++;
            rleList.head = currentNode.next;
          }else {
            currentNode.next.prev = added;
            added.next = currentNode.next;
            rleList.head = added;
          }
        }
      }
    }else if (pixelLocator == currentNode.lengthOfRun) {
      if (currentNode.next != null) {
        if (currentNode.next.redIntensity == red && currentNode.next.greenIntensity == green && currentNode.next.blueIntensity == blue) {
          if (currentNode.lengthOfRun > 1) {
            currentNode.lengthOfRun--;
            currentNode.next.lengthOfRun++;
          }else if (currentNode.lengthOfRun == 1) {
            if (currentNode.prev == null) {
              currentNode.next.lengthOfRun++;
              rleList.head = currentNode.next;
            }else if (currentNode.prev.redIntensity == red && currentNode.prev.greenIntensity == green && currentNode.prev.blueIntensity == blue) {
              currentNode.prev.lengthOfRun = (short) (currentNode.prev.lengthOfRun + currentNode.next.lengthOfRun + 1);
              currentNode.prev.next = currentNode.next.next;
              currentNode.next.next.prev = currentNode.prev;
            }else {
              currentNode.next.lengthOfRun++;
              currentNode.prev.next = currentNode.next;
              currentNode.next.prev = currentNode.prev;
            }
          }       
        }else { 
          if (currentNode.lengthOfRun > 1) {
            currentNode.next.prev = added;
            added.next = currentNode.next;
            currentNode.lengthOfRun--;
            currentNode.next = added;
            added.prev = currentNode;
          }else if (currentNode.lengthOfRun == 1) {
            if (currentNode.prev == null) {
              added.next = currentNode.next;
              currentNode.next.prev = added;
              rleList.head = added;
            }else if (currentNode.prev.redIntensity == red && currentNode.prev.greenIntensity == green && currentNode.prev.blueIntensity == blue) {
              currentNode.prev.lengthOfRun++;
              currentNode.prev.next = currentNode.next;
              currentNode.next.prev = currentNode.prev;
            }else {
              currentNode.next.prev = added;
              added.next = currentNode.next;
              added.prev = currentNode.prev;
              currentNode.prev.next = added;
            }    
          }
        }
      }else if (currentNode.next == null) {
        if (currentNode.lengthOfRun > 1) {
          currentNode.lengthOfRun--;
          currentNode.next = added;
          added.prev = currentNode;
          rleList.tail = added;
        }else if (currentNode.lengthOfRun == 1) {
          if (currentNode.prev.redIntensity == red && currentNode.prev.greenIntensity == green && currentNode.prev.blueIntensity == blue) {
            currentNode.prev.lengthOfRun++;
            rleList.tail = currentNode.prev;
          }else {
            currentNode.prev.next = added;
            added.prev = currentNode.prev;
            rleList.tail = added;
          }   
        }
      }
    }else if (pixelLocator < currentNode.lengthOfRun && pixelLocator > 1) {
        int[] partNodeListLeft = new int[4];
        int[] partNodeListRight = new int[4];
        partNodeListLeft[0] = currentNode.redIntensity;
        partNodeListLeft[1] = currentNode.greenIntensity;
        partNodeListLeft[2] = currentNode.blueIntensity;
        partNodeListLeft[3] = currentNode.lengthOfRun - pixelLocator;
        //System.out.println("partleft Length: "+partNodeListLeft[3]);


        partNodeListRight[0] = currentNode.redIntensity;
        partNodeListRight[1] = currentNode.greenIntensity;
        partNodeListRight[2] = currentNode.blueIntensity;
        partNodeListRight[3] = pixelLocator - 1;
        //System.out.println("partright Length: "+partNodeListRight[3]);

        DListNode partLeft = new DListNode(partNodeListLeft);
        DListNode partRight = new DListNode(partNodeListRight);

        //System.out.println("partLeft is: "+partLeft);
        //System.out.println("partRight is: "+partRight);

        if (currentNode.prev != null && currentNode.next != null) {

          currentNode.prev.next = partLeft;
          partLeft.prev = currentNode.prev;
          partLeft.next = added;
          added.prev = partLeft;
          added.next = partRight;
          partRight.prev = added;
          currentNode.next.prev = partRight;
          partRight.next = currentNode.next;
        }else if (currentNode.prev == null) {
          added.prev = partLeft;
          partLeft.next = added;
          added.next = partRight;
          partRight.prev = added;
          currentNode.next.prev = partRight;
          partRight.next = currentNode.next;
          rleList.head = partLeft;
        }else if (currentNode.next == null) {
          currentNode.prev.next = partLeft;
          partLeft.prev = currentNode.prev;
          partLeft.next = added;
          added.prev = partLeft;
          added.next = partRight;
          partRight.prev = added;
          rleList.tail = added;
        }     
      }
      
    check();
  }



  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
  }
}
