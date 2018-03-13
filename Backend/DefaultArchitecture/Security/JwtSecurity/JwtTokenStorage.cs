using Domain.Entities;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DefaultArchitecture.Security.JwtSecurity
{
    public class JwtTokenStorage
    {
        private static Hashtable hashtable = new Hashtable();

        public static void RefreshToken(string oldToken, string newToken)
        {
            var item = hashtable[oldToken];
            if(item != null)
            {
                hashtable[oldToken] = null;
                hashtable[newToken] = item;
            }
        }

        public static bool TokenExists(string token)
        {
            return hashtable[token] != null;
        }

        public static void AddToken(long userId, string token)
        {
            hashtable.Add(token, userId);
        }

        public static void InvalidateToken(string token)
        {
            hashtable[token] = null;
        }

        public static bool IsLoggedIn(long userId, string token)
        {
            var user = hashtable[token];
            return user != null && (long)user == userId;
        }
    }
}
