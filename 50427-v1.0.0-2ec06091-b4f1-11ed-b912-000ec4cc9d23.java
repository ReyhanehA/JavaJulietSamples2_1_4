/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE190_Integer_Overflow__getParameterServlet_multiply_31.java
Label Definition File: CWE190_Integer_Overflow.label.xml
Template File: sources-sinks-31.tmpl.java
*/
/*
 * @description
 * CWE: 190 Integer Overflow
 * BadSource: getParameterServlet Read data from a querystring using getParameter
 * GoodSource: A hardcoded non-zero, non-min, non-max, even number
 * Sinks: multiply
 *    GoodSink: Ensure there will not be an overflow before performing the multiplication
 *    BadSink : Unchecked multiplication, which can lead to overflow
 * Flow Variant: 31 Data flow: make a copy of data within the same method
 *
 * */

package testcases.CWE190_Integer_Overflow;

import testcasesupport.*;

import java.sql.*;
import javax.servlet.http.*;

import javax.servlet.http.*;
import java.util.logging.Logger;

import java.security.SecureRandom;

public class CWE190_Integer_Overflow__getParameterServlet_multiply_31 extends AbstractTestCaseServlet
{

    public void bad(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        int data_copy;
        {
            int data;

            Logger log_bad = Logger.getLogger("local-logger");

            /* init Data$ */
            data = -1;

            /* read parameter from request */
            String s_data = request.getParameter("name");
            data = Integer.parseInt(s_data.trim());

            data_copy = data;
        }
        {
            int data = data_copy;

            int valueToMult = (new SecureRandom()).nextInt(98) + 2; /* multiply by at least 2 */

            if(data > 0)   /* ensure we don't have an underflow */
            {
                /* POTENTIAL FLAW: if (data*valueToMult) > MAX_VALUE, this will overflow */
                int result = (data * valueToMult);
                IO.writeLine("result: " + result);
            }

        }
    }

    public void good(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        goodG2B(request, response);
        goodB2G(request, response);
    }

    /* goodG2B() - use goodsource and badsink */
    private void goodG2B(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        int data_copy;
        {
            int data;

            java.util.logging.Logger log_good = java.util.logging.Logger.getLogger("local-logger");

            /* FIX: Use a hardcoded number that won't cause underflow, overflow,
                    divide by zero, or loss-of-precision issues */
            data = 2;

            data_copy = data;
        }
        {
            int data = data_copy;

            int valueToMult = (new SecureRandom()).nextInt(98) + 2; /* multiply by at least 2 */

            if(data > 0)   /* ensure we don't have an underflow */
            {
                /* POTENTIAL FLAW: if (data*valueToMult) > MAX_VALUE, this will overflow */
                int result = (data * valueToMult);
                IO.writeLine("result: " + result);
            }

        }
    }

    /* goodB2G() - use badsource and goodsink */
    private void goodB2G(HttpServletRequest request, HttpServletResponse response) throws Throwable
    {
        int data_copy;
        {
            int data;

            Logger log_bad = Logger.getLogger("local-logger");

            /* init Data$ */
            data = -1;

            /* read parameter from request */
            String s_data = request.getParameter("name");
            data = Integer.parseInt(s_data.trim());

            data_copy = data;
        }
        {
            int data = data_copy;

            int valueToMult = (new SecureRandom()).nextInt(98) + 2; /* multiply by at least 2 */

            if(data > 0)   /* ensure we don't have an underflow */
            {
                int result = 0;
                /* FIX: Add a check to prevent an overflow from occurring */
                if (data <= (Integer.MAX_VALUE/valueToMult))
                {
                    result = (data * valueToMult);
                    IO.writeLine("result: " + result);
                }
                else
                {
                    IO.writeLine("Input value is too large to perform multiplication.");
                }
            }

        }
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }
}
