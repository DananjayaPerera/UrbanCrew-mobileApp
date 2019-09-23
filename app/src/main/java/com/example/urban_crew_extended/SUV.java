package com.example.urban_crew_extended;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SUV.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SUV#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SUV extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SUV() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SUV.
     */
    // TODO: Rename and change types and number of parameters
    public static SUV newInstance(String param1, String param2) {
        SUV fragment = new SUV();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suv, container, false);

        CardView cardView9 = (CardView)view.findViewById(R.id.cardView_9);
        cardView9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent9 = new Intent(getActivity(), CR_V.class);

                startActivity(intent9);

            }
        });

        CardView cardView10 = (CardView)view.findViewById(R.id.cardView_10);
        cardView10.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(getActivity(), X_Trail.class);

                startActivity(intent10);

            }
        });

        CardView cardView11 = (CardView)view.findViewById(R.id.cardView_11);
        cardView11.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(getActivity(), V8.class);

                startActivity(intent11);

            }
        });

        CardView cardView12 = (CardView)view.findViewById(R.id.cardView_12);
        cardView12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12 = new Intent(getActivity(), Evoque.class);

                startActivity(intent12);

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
