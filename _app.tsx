import "@/styles/globals.css";
import type { AppProps } from "next/app";
import { Provider } from "react-redux";
import store, { setUser } from "@/store";
import Navbar from "@/components/Navbar";
import Head from "next/head";
//import { Component } from "lucide-react";
import { useEffect } from "react";
import Footer from "@/components/ui/Fotter";

const Myapp = ({ Component, pageProps }: AppProps) => {
  useEffect(() => {
    const storeduser = localStorage.getItem("user");
    if (storeduser) {
      store.dispatch(setUser(JSON.parse(storeduser)));
    }
  }, []);
  return (
    <div>
      <div className="min-h-screen">
        <Navbar />
        <Component {...pageProps} />
        <Footer/>
      </div>
    </div>
  );
};
export default function App( props : AppProps) {
  return (
    <Provider store={store}>
      <Head>
        <title>MakeMyTour</title>
      </Head>
      <Myapp {...props} />
    </Provider>
  );
}
