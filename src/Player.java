// Class 'Player'

import java.io.Serializable;

abstract class Player implements Serializable {

        String name, contactNumber, emailID, modeOfPayment, studentID;
        boolean paid;

        abstract void setData ();
        // abstract void editData ();
        abstract void getData ();
}
