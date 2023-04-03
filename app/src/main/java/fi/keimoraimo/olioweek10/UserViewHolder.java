package fi.keimoraimo.olioweek10;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView firstName, lastName, email, degreeProgram, degrees;

    public UserViewHolder(@NonNull View itemView){
        super(itemView);
        firstName = itemView.findViewById(R.id.userViewFirstName);
        lastName = itemView.findViewById(R.id.userViewLastName);
        email = itemView.findViewById(R.id.userViewEmail);
        degreeProgram = itemView.findViewById(R.id.userViewDegreeProgram);
        degrees =itemView.findViewById(R.id.userViewDegrees);
    }

    public void bind(User user) {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        degreeProgram.setText(user.getDegreeProgram());

        if (user.getDegrees().size() != 0) {
            StringBuilder degreesText = new StringBuilder();
            degreesText.append("Suoritetut tutkinnot:\n");
            for (String degree : user.getDegrees()) {
                degreesText.append("-").append(degree).append("\n");
            }
            degrees.setText(degreesText.toString().trim());
        } else {
            degrees.setText(null);
        }

    }
}
