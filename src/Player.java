// Class 'Player'

import java.io.Serializable;

abstract class Player implements Serializable {

        int serialNumber;
        String name, contactNumber, emailID, modeOfPayment, studentID;
        boolean paid;

        abstract void setData ();
        abstract void getData ();
}
