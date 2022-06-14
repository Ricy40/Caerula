tag @s[tag=roomtag1] add roomtag2
execute as @s[tag=!roomtag1] run function expansion:items/transporter/initiate
execute as @s[tag=roomtag2] run function expansion:items/transporter/return