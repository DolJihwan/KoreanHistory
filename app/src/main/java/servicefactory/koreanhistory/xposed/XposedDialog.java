package servicefactory.koreanhistory.xposed;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import servicefactory.koreanhistory.R;
import servicefactory.koreanhistory.activity.QuizActivity;
import servicefactory.koreanhistory.contorller.QuizController;
import servicefactory.koreanhistory.contorller.WrongAnswerController;
import servicefactory.koreanhistory.popup.TextDrawable;

/**
 * Created by naman on 20/03/15.
 */
public class XposedDialog extends DialogFragment {

    private WrongAnswerController wrongAnswerController;

    public XposedDialog() {

    }

    LinearLayout bt_entireQuiz, bt_reviewQuiz, bt_deleteQuiz, bt_cancel;
    private int backgroundColor;
    private String mDatetime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_power, container, false);
        Bundle mArgs = getArguments();
        mDatetime = mArgs.getString("datetime");

        backgroundColor = Color.parseColor("#ffffff");
        bt_entireQuiz = (LinearLayout) view.findViewById(R.id.power);
        bt_reviewQuiz = (LinearLayout) view.findViewById(R.id.reboot);
        bt_deleteQuiz = (LinearLayout) view.findViewById(R.id.soft_reboot);
        bt_cancel = (LinearLayout) view.findViewById(R.id.ll_cancel);

        wrongAnswerController = new WrongAnswerController(getActivity());

        bt_entireQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra("datetime", mDatetime);
                intent.putExtra("activityFlag", "entire");
                startActivity(intent);
                onDismiss(getDialog());
                getActivity().finish();

                Toast.makeText(getActivity(), "Entire Quiz " + mDatetime, Toast.LENGTH_SHORT).show();
            }
        });
        bt_reviewQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), QuizActivity.class);
                intent.putExtra("datetime", mDatetime);
                intent.putExtra("activityFlag", "review");
                startActivity(intent);
                onDismiss(getDialog());
                getActivity().finish();

                Toast.makeText(getActivity(), "Review Quiz " + mDatetime, Toast.LENGTH_SHORT).show();
            }
        });
        bt_deleteQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogSimple();
                Toast.makeText(getActivity(), "Delete Quiz", Toast.LENGTH_SHORT).show();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDismiss(getDialog());
                Toast.makeText(getActivity(), "취소", Toast.LENGTH_SHORT).show();
            }
        });
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        TextDrawable drawable1 = TextDrawable.builder()
                .buildRound("A", Color.parseColor("#d32f2f"));
        ((ImageView) view.findViewById(R.id.ipower)).setImageDrawable(drawable1);

        TextDrawable drawable3 = TextDrawable.builder()
                .buildRound("C", Color.parseColor("#009688"));
        ((ImageView) view.findViewById(R.id.ibootloader)).setImageDrawable(drawable3);

        TextDrawable drawable5 = TextDrawable.builder()
                .buildRound("D", Color.parseColor("#e91e63"));
        ((ImageView) view.findViewById(R.id.isoftreboot)).setImageDrawable(drawable5);

        TextDrawable drawable6 = TextDrawable.builder()
                .buildRound("R", Color.parseColor("#3f51b5"));
        ((ImageView) view.findViewById(R.id.ireboot)).setImageDrawable(drawable6);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;

        window.setAttributes(windowParams);
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity != null && activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    private void DialogSimple() {
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(getActivity());
        alt_bld.setMessage("삭제하시겠습니까?").setCancelable(
                false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        wrongAnswerController.deleteReview(mDatetime);
                        onDismiss(getDialog());
                        // Action for 'Yes' Button
                    }
                }).setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Action for 'NO' Button
                        dialog.cancel();
                    }
                });
        AlertDialog alert = alt_bld.create();
        // Title for AlertDialog
        alert.setTitle("Title");
        // Icon for AlertDialog
        alert.setIcon(R.drawable.korea);
        alert.show();
    }
}

